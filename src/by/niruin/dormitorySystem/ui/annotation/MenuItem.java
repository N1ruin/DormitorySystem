package by.niruin.dormitorySystem.ui.annotation;

import by.niruin.dormitorySystem.domain.model.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MenuItem {
    String itemAction();

    Role[] allowedRoles() default {};
}
