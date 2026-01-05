package by.niruin.dormitorySystem.domain.service;

import by.niruin.dormitorySystem.domain.context.ApplicationContextHolder;
import by.niruin.dormitorySystem.domain.model.dto.*;
import by.niruin.dormitorySystem.domain.model.Room;
import by.niruin.dormitorySystem.domain.model.Student;
import by.niruin.dormitorySystem.domain.repository.RoomRepository;
import by.niruin.dormitorySystem.domain.repository.StudentRepository;
import by.niruin.dormitorySystem.domain.service.validation.RoomValidationService;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.ui.menu.SelectOrderRoomMenuItem;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class RoomService {
    public static final String ROOM_INFORMATION = "Информация о комнате №%d: \n";
    public static final String MALE = "Male: %s\n";
    public static final String ROOM_CAPACITY = "Количество мест:%d\n";
    public static final String ROOM_IS_FULL = "Free capacity: 0\n";
    public static final String FREE_CAPACITY = "Free capacity: %d\n";
    public static final String STUDENTS_IN_ROOM_LIST = "Students in room:\n";
    public static final String IS_AVAILABLE = "Room is available for living:%s \n";
    public static final String NEW_LINE_SYMBOL = "\n";
    private final RoomValidationService roomValidationService;
    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;

    public RoomService(RoomValidationService roomValidationService,
                       RoomRepository roomRepository,
                       StudentRepository studentRepository) {
        this.roomValidationService = roomValidationService;
        this.roomRepository = roomRepository;

        this.studentRepository = studentRepository;
    }

    public void createRoom(CreateRoomDto dto) {
        roomValidationService.validateCreateData(dto);

        UUID currentDormitoryId = ApplicationContextHolder.getContext().getActiveUser().getDormitoryId();
        UUID roomId = UUID.randomUUID();

        Room room = new Room(roomId, dto.capacity(), dto.number(), dto.availableForLiving(), dto.isMaleOnly(), currentDormitoryId);
        roomRepository.save(room);
    }

    public void deleteRoom(DeleteRoomDto dto) {
        roomValidationService.validateRoomNumber(dto.number());
        var room = roomRepository.findByNumber(dto.number())
                .orElseThrow(() -> new EntityNotFoundException(dto.number(), Room.class));
        roomRepository.delete(room.getId());
    }

    public void updateRoom(UpdateRoomDto dto) {
        roomValidationService.validateRoomNumber(dto.number());

        var room = roomRepository.findByNumber(dto.number())
                .orElseThrow(() -> new EntityNotFoundException(dto.number(), Room.class));
        room.setCapacity(dto.capacity());
        room.setAvailableForLiving(dto.availableForLiving());
        room.setMaleOnly(dto.isMale());
        roomRepository.update(room);
    }

    public String getRoomInfo(GetRoomInfoDto dto) {
        var room = roomRepository.findByNumber(dto.number()).orElseThrow(
                () -> new EntityNotFoundException(dto.number(), Room.class));

        return buildRoomInfo(room);
    }

    public String getSortedRoomsInfo(SelectOrderRoomMenuItem item) {
        List<Room> rooms = roomRepository.findAllOrderBy(getRoomComparator(item));
        return buildRoomInfo(rooms.toArray(Room[]::new));
    }

    public RoomNumbersDto getRoomNumbers() {
        String numbers = roomRepository.findByCurrentDormitoryId().stream()
                .map(Room::getNumber)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining("\n"));

        return new RoomNumbersDto(numbers);
    }

    private Comparator<Room> getRoomComparator(SelectOrderRoomMenuItem item) {
        return switch (item) {
            case SORT_BY_NUMBER -> Comparator.comparingInt(Room::getNumber);
            case SORT_BY_NUMBER_DESC -> Comparator.comparingInt(Room::getNumber).reversed();
            case SORT_BY_FREE_QUANTITY -> Comparator.comparingInt(this::getFreePlaces);
            case SORT_BY_FREE_QUANTITY_DESC -> Comparator.comparingInt(this::getFreePlaces).reversed();
            case SORT_BY_GENDER_MALE_FIRST -> Comparator.comparing(Room::isMaleOnly).reversed();
            case SORT_BY_GENDER_FEMALE_FIRST -> Comparator.comparing(Room::isMaleOnly);
            case SORT_BY_AVAILABLE_FOR_LIVING -> Comparator.comparing(Room::isAvailableForLiving).reversed();
            case SORT_BY_AVAILABLE_FOR_LIVING_DESC -> Comparator.comparing(Room::isAvailableForLiving);
        };
    }

    private int getFreePlaces(Room room) {
        List<Student> students = studentRepository.getStudentsInRooms().get(room.getId());
        return room.getCapacity() - (students != null ? students.size() : 0);
    }

    private String buildRoomInfo(Room... rooms) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Room room : rooms) {
            stringBuilder.append(ROOM_INFORMATION.formatted(room.getNumber()));
            stringBuilder.append(ROOM_CAPACITY.formatted(room.getCapacity()));
            stringBuilder.append(MALE.formatted(room.isMaleOnly()));

            List<Student> studentsInRoom = studentRepository.getStudentsInRooms().get(room.getId());
            if (studentsInRoom == null) {
                stringBuilder.append(ROOM_IS_FULL);
            } else {
                int freeCapacity = room.getCapacity() - studentsInRoom.size();

                stringBuilder.append(FREE_CAPACITY.formatted(freeCapacity));
                stringBuilder.append(STUDENTS_IN_ROOM_LIST);
                studentsInRoom.forEach(student -> stringBuilder.append(student.getFullName().getShortName()).append(NEW_LINE_SYMBOL));
            }

            stringBuilder.append(IS_AVAILABLE.formatted(room.isAvailableForLiving()));
        }
        return stringBuilder.toString();
    }

}
