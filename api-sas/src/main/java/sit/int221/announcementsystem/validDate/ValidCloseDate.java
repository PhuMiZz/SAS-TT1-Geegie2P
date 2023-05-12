package sit.int221.announcementsystem.validDate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CloseDateValidator.class)
public @interface ValidCloseDate {
    String message() default "Close date must be after publish date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
