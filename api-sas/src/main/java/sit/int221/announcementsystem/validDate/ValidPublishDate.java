package sit.int221.announcementsystem.validDate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PublishDateValidator.class)
public @interface ValidPublishDate {
    String message() default "Invalid publish date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
