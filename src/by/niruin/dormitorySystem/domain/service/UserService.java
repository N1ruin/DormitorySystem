package by.niruin.dormitorySystem.domain.service;

import by.niruin.dormitorySystem.domain.model.*;
import by.niruin.dormitorySystem.domain.model.dto.user.DeleteUserDto;
import by.niruin.dormitorySystem.domain.model.dto.user.UpdateUserDto;
import by.niruin.dormitorySystem.domain.model.dto.user.UserInfoDto;
import by.niruin.dormitorySystem.domain.model.dto.user.UserLoginDto;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;
import by.niruin.dormitorySystem.domain.repository.UserRepository;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.formatter.UserFormatter;

import java.util.Comparator;

@Component
public class UserService {
    private final UserRepository userRepository;
    private final UniversityRepository universityRepository;
    private final DormitoryRepository dormitoryRepository;
    private final UserFormatter userFormatter;

    public UserService(UserRepository userRepository, UniversityRepository universityRepository, DormitoryRepository dormitoryRepository, UserFormatter userFormatter) {
        this.userRepository = userRepository;
        this.universityRepository = universityRepository;
        this.dormitoryRepository = dormitoryRepository;
        this.userFormatter = userFormatter;
    }

    public void delete(DeleteUserDto dto) {
        var user = userRepository.findByLogin(dto.login())
                .orElseThrow(() -> new EntityNotFoundException(dto.login(), User.class));
        userRepository.delete(user.getId());
    }

    public void update(UpdateUserDto dto) {
        var user = userRepository.findByLogin(dto.login())
                .orElseThrow(() -> new EntityNotFoundException(dto.login(), User.class));

        user.setLogin(dto.login());
        user.setPasswordHash(dto.password());
        var fullName = user.getFullName();
        fullName.setLastName(dto.lastName());
        user.setRole(dto.role());
    }

    public String getUserInfo(UserLoginDto dto) {
        var user = userRepository.findByLogin(dto.login())
                .orElseThrow(() -> new EntityNotFoundException(dto.login(), User.class));

        var infoDto = buildInfoDto(user);

        return userFormatter.formatStudentsToStudentsInfo(infoDto);
    }

    public String getUsersInfo(Comparator<User> comparator) {
        var sortedUsersList = userRepository.findAllOrderBy(comparator);

        var infoDtoList = sortedUsersList.stream()
                .map(this::buildInfoDto)
                .toList();

        return userFormatter.formatStudentsToStudentsInfo(infoDtoList.toArray(UserInfoDto[]::new));
    }

    private UserInfoDto buildInfoDto(User user) {
        var login = user.getLogin();
        var fullName = user.getFullName().getFullNameString();
        var gender = user.getGender();

        String universityName = universityRepository.findById(user.getUniversityId())
                .map(University::getName)
                .orElse(null);

        Integer dormitoryNumber = null;
        if (user.getDormitoryId() != null) {
            dormitoryNumber = dormitoryRepository.findById(user.getDormitoryId())
                    .map(Dormitory::getNumber)
                    .orElse(null);
        }

        return new UserInfoDto(login, fullName, gender, universityName, dormitoryNumber);
    }
}
