package sit.int221.announcementsystem.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = DisplayValidator.class)
public @interface ValidDisplay {
    String regexp();
    String message() default "must be either 'Y' or 'N'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
