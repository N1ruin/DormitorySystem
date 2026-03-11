package by.niruin.dormitorySystem.ui.menu.service;

import by.niruin.dormitorySystem.domain.context.ApplicationContextHolder;
import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.menu.item.MenuItem;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.INVALID_INPUT_MESSAGE;
import static by.niruin.dormitorySystem.constant.LoggerMessage.FIELD_NOT_FOUND_FOR_ENUM_LOG;

@Component
public class MenuItemService {
    private static final Logger logger = LoggerFactory.getLogger(MenuItemService.class);

    public <E extends Enum<E>> String buildMenu(Class<E> enumClass) {
        List<String> allowedItems = getItemsActions(enumClass);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < allowedItems.size(); i++) {
            stringBuilder.append(i + 1)
                    .append(". ")
                    .append(allowedItems.get(i))
                    .append("\n");
        }

        return stringBuilder.toString();
    }

    public <E extends Enum<E>> E getItem(Class<E> enumClass, int number) {
        List<E> allowedItems = getAllowedItems(enumClass);

        if (number < 1 || number > allowedItems.size()) {
            throw new RuntimeException(INVALID_INPUT_MESSAGE);
        }

        return allowedItems.get(number - 1);
    }

    private <E extends Enum<E>> List<String> getItemsActions(Class<E> enumClass) {
        List<E> allowedItems = getAllowedItems(enumClass);
        List<String> actions = new ArrayList<>();

        for (var item : allowedItems) {
            try {
                Field field = item.getClass().getField(item.name());
                MenuItem annotation = field.getAnnotation(MenuItem.class);
                actions.add(annotation.itemAction());
            } catch (NoSuchFieldException e) {
                logger.warn(FIELD_NOT_FOUND_FOR_ENUM_LOG.formatted(item.name()));
                throw new RuntimeException(e);
            }
        }
        return actions;
    }

    private <E extends Enum<E>> List<E> getAllowedItems(Class<E> enumClass) {
        List<E> allowedItems = new ArrayList<>();
        User currentUser = ApplicationContextHolder.getContext().getActiveUser();

        if (currentUser == null) {
            return List.of(enumClass.getEnumConstants());
        }

        Role currentUserRole = ApplicationContextHolder.getContext().getActiveUser().getRole();
        for (E enumConstant : enumClass.getEnumConstants()) {
            try {
                Field field = enumClass.getField(enumConstant.name());

                if (!field.isAnnotationPresent(MenuItem.class)) {
                    continue;
                }

                MenuItem annotation = field.getAnnotation(MenuItem.class);
                Role[] allowedRoles = annotation.allowedRoles();

                if (isUserHasPermission(currentUserRole, allowedRoles)) {
                    allowedItems.add(enumConstant);
                }
            } catch (NoSuchFieldException e) {
                logger.warn(FIELD_NOT_FOUND_FOR_ENUM_LOG.formatted(enumConstant.name()));
                throw new RuntimeException(e);
            }
        }
        return allowedItems;
    }

    private boolean isUserHasPermission(Role userRole, Role[] allowedRoles) {
        if (allowedRoles.length == 0) {
            return true;
        } else {
            return Arrays.asList(allowedRoles).contains(userRole);
        }
    }
}
