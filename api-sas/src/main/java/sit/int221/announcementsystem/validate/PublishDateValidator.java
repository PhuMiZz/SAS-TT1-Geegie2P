package sit.int221.announcementsystem.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.ZonedDateTime;

public class PublishDateValidator implements ConstraintValidator<ValidPublishDate, ZonedDateTime> {
    @Override
    public boolean isValid(ZonedDateTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        ZonedDateTime now = ZonedDateTime.now();
        if (value.isEqual(now)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("must be later than publish date")
                    .addConstraintViolation();
            return false;
        } else if (value.isBefore(now)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("must be a date in the present or in the future")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}

