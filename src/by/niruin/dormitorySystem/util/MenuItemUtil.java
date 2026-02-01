package by.niruin.dormitorySystem.util;

import by.niruin.dormitorySystem.domain.context.ApplicationContextHolder;
import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.annotation.MenuItem;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuItemUtil {
    private static final Logger logger = LoggerFactory.getLogger(MenuItemUtil.class);

    public static <E extends Enum<E>> String buildMenu(Class<E> enumClass) {
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

    public static <E extends Enum<E>> E getItem(Class<E> enumClass, int number) {
        List<E> allowedItems = getAllowedItems(enumClass);

        if (number < 1 || number > allowedItems.size()) {
            throw new RuntimeException("Incorrent input, please try again");
        }

        return allowedItems.get(number - 1);
    }

    private static <E extends Enum<E>> List<String> getItemsActions(Class<E> enumClass) {
        List<E> allowedItems = getAllowedItems(enumClass);
        List<String> actions = new ArrayList<>();

        for (var item : allowedItems) {
            try {
                Field field = item.getClass().getField(item.name());
                MenuItem annotation = field.getAnnotation(MenuItem.class);
                actions.add(annotation.itemAction());
            } catch (NoSuchFieldException e) {
                logger.warn("Field not found for enum constant:" + item.name());
                throw new RuntimeException(e);
            }
        }
        return actions;
    }

    private static <E extends Enum<E>> List<E> getAllowedItems(Class<E> enumClass) {
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
                logger.warn("Field not found for enum constant:" + enumConstant.name());
                throw new RuntimeException(e);
            }
        }
        return allowedItems;
    }

    private static boolean isUserHasPermission(Role userRole, Role[] allowedRoles) {
        if (allowedRoles.length == 0) {
            return true;
        } else {
            return Arrays.asList(allowedRoles).contains(userRole);
        }
    }
}
