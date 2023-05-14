package sit.int221.announcementsystem.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

@Target({ElementType.FIELD, TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DisplayValidator.class)
public @interface ValidDisplay {
    String message() default "must be either 'Y' or 'N'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
