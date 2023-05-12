package sit.int221.announcementsystem.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import sit.int221.announcementsystem.validators.ValidDate;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;

@Documented
@Constraint(validatedBy = ValidDate.class)
@Target({ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateTimeConstraints {
    String message() default
            "End date must be after begin date and both must be in the future";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
