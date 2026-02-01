package by.niruin.dormitorySystem.domain.service;

import by.niruin.dormitorySystem.domain.model.Gender;
import by.niruin.dormitorySystem.domain.model.Room;
import by.niruin.dormitorySystem.domain.model.Student;
import by.niruin.dormitorySystem.domain.model.dto.room.*;
import by.niruin.dormitorySystem.domain.repository.RoomRepository;
import by.niruin.dormitorySystem.domain.repository.StudentRepository;
import by.niruin.dormitorySystem.domain.service.validation.RoomValidationService;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.formatter.RoomFormatter;
import by.niruin.dormitorySystem.util.ApplicationContextUtil;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class RoomService {
    private final RoomValidationService roomValidationService;
    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;
    private final RoomFormatter roomFormatter;

    public RoomService(RoomValidationService roomValidationService,
                       RoomRepository roomRepository,
                       StudentRepository studentRepository, RoomFormatter roomFormatter) {
        this.roomValidationService = roomValidationService;
        this.roomRepository = roomRepository;

        this.studentRepository = studentRepository;
        this.roomFormatter = roomFormatter;
    }

    public void createRoom(CreateRoomDto dto) {
        roomValidationService.validateCreateData(dto);

        UUID currentDormitoryId = ApplicationContextUtil.getCurrentDormitoryId();
        UUID roomId = UUID.randomUUID();

        Room room = new Room(roomId, dto.capacity(), dto.number(), dto.availableForLiving(), dto.isMaleOnly(), currentDormitoryId);
        roomRepository.save(room);
    }

    public void deleteRoom(DeleteRoomDto dto) {
        var roomId = getRoomIdFromCurrentUniversityByListNumber(dto.numberFromList());

        roomRepository.delete(roomId);
    }

    public void updateRoom(UpdateRoomDto dto) {
        roomValidationService.validateRoomNumber(dto.number());

        var room = roomRepository.findByNumber(ApplicationContextUtil.getCurrentDormitoryId(), dto.number())
                .orElseThrow(() -> new EntityNotFoundException(dto.number(), Room.class));
        room.setCapacity(dto.capacity());
        room.setAvailableForLiving(dto.availableForLiving());
        room.setMaleOnly(dto.isMale());

        roomRepository.update(room);
    }

    public String getRoomInfo(RoomNumberFromListDto dto) {
        var room = roomRepository.findByNumber(ApplicationContextUtil.getCurrentDormitoryId(), dto.numberFromList()).orElseThrow(
                () -> new EntityNotFoundException(dto.numberFromList(), Room.class));

        var roomInfoDto = buildRoomInfoDto(room);

        return roomFormatter.formatRoomsToRoomsInfo(roomInfoDto);
    }

    public String getSortedRoomsInfo(Comparator<Room> comparator) {
        var roomList = roomRepository.findAllByDormitoryIdOrderBy(ApplicationContextUtil.getCurrentDormitoryId(), comparator);

        var roomInfoDtos = roomList.stream()
                .map(this::buildRoomInfoDto)
                .toList();

        return roomFormatter.formatRoomsToRoomsInfo(roomInfoDtos.toArray(RoomInfoDto[]::new));
    }

    public RoomNumbersDto getRoomNumbers() {
        String numbers = roomRepository.findByDormitoryId(ApplicationContextUtil.getCurrentDormitoryId()).stream()
                .map(Room::getNumber)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining("\n"));

        return new RoomNumbersDto(numbers);
    }

    public UUID getRoomIdFromCurrentUniversityByListNumber(int numberFromList) {
        return roomRepository.findByDormitoryId(ApplicationContextUtil.getCurrentDormitoryId()).stream()
                .sorted()
                .toList()
                .get(numberFromList - 1)
                .getId();
    }

    public int getFreePlaces(UUID roomId) {
        var room = roomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException(roomId, Room.class));

        var studentsList = studentRepository.findByDormitoryIdGroupingByRoomId(ApplicationContextUtil.getCurrentDormitoryId(), roomId).get(roomId);

        return room.getCapacity() - (studentsList != null ? studentsList.size() : 0);
    }

    public List<Room> getFreeRooms(Gender gender, UUID dormitoryId) {
        var roomsOccupancy = studentRepository
                .findByDormitoryId(dormitoryId).stream()
                .filter(student -> student.getRoomId() != null)
                .collect(Collectors.groupingBy(
                        Student::getRoomId,
                        Collectors.counting()
                ));

        return roomRepository.findByDormitoryId(dormitoryId).stream()
                .filter(room -> (gender == Gender.MALE) == room.isMaleOnly())
                .filter(Room::isAvailableForLiving)
                .filter(room -> {
                    long occupied = roomsOccupancy.getOrDefault(room.getId(), 0L);
                    return occupied < room.getCapacity();
                })
                .sorted(Comparator.comparingInt(Room::getNumber))
                .toList();
    }

    public RoomNumbersDto getFreeRoomsNumbers(Gender gender) {
        List<Room> freeRooms = getFreeRooms(gender, ApplicationContextUtil.getCurrentDormitoryId());

        if (freeRooms.isEmpty()) {
            return new RoomNumbersDto("");
        }

        String roomNumbers = freeRooms.stream()
                .map(room -> String.format("Room №%d - Free places: %d/%d",
                        room.getNumber(),
                        getFreePlaces(room.getId()),
                        room.getCapacity()))
                .collect(Collectors.joining("\n"));

        return new RoomNumbersDto(roomNumbers);
    }

    public List<Room> getFreeRooms() {
        UUID currentDormitoryId = ApplicationContextUtil.getCurrentDormitoryId();
        List<Student> studentsWithoutRoom = getStudentsWithoutRoom(currentDormitoryId);

        if (studentsWithoutRoom.isEmpty()) {
            return List.of();
        }

        Map<Gender, List<Student>> studentsByGender = studentsWithoutRoom.stream()
                .collect(Collectors.groupingBy(Student::getGender));

        List<Room> allFreeRooms = new ArrayList<>();

        for (Gender gender : studentsByGender.keySet()) {
            List<Room> freeRoomsForGender = getFreeRooms(gender, currentDormitoryId);
            allFreeRooms.addAll(freeRoomsForGender);
        }

        return allFreeRooms.stream()
                .distinct()
                .sorted(Comparator.comparingInt(Room::getNumber))
                .toList();
    }

    private RoomInfoDto buildRoomInfoDto(Room room) {
        var number = room.getNumber();
        var gender = room.isMaleOnly() ? Gender.MALE : Gender.FEMALE;
        var capacity = (int) room.getCapacity();
        var isFull = getFreePlaces(room.getId()) == 0;
        var inhabitantsCount = capacity - getFreePlaces(room.getId());
        var availableForLiving = room.isAvailableForLiving();

        var studentsFromRoomList = studentRepository.findByRoomId(room.getId()).stream()
                .map(student -> student.getFullName().getFullNameString())
                .toList();

        return new RoomInfoDto(number, gender, capacity, isFull, inhabitantsCount, availableForLiving, studentsFromRoomList);
    }

    private List<Student> getStudentsWithoutRoom(UUID dormitoryId) {
        return studentRepository.findByDormitoryId(dormitoryId).stream()
                .filter(student -> student.getRoomId() == null)
                .toList();
    }
}
