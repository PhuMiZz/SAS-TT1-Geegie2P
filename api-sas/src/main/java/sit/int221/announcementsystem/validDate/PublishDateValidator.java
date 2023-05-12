package sit.int221.announcementsystem.validDate;

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
            context.buildConstraintViolationWithTemplate("publishDate must be in the present")
                    .addConstraintViolation();
            return false;
        } else if (value.isBefore(now)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("publishDate must be in the future")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
