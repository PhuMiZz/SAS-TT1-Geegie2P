package sit.int221.announcementsystem.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;
import sit.int221.announcementsystem.annotations.DateTimeConstraints;
import sit.int221.announcementsystem.dtos.AnnouncementCreateUpdateDto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ValidDate implements ConstraintValidator<DateTimeConstraints, AnnouncementCreateUpdateDto> {

    @Override
    public void initialize(DateTimeConstraints constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(AnnouncementCreateUpdateDto dto, ConstraintValidatorContext constraintValidatorContext) {

        LocalDateTime publicDate = dto.getPublishDate().toLocalDateTime();
        LocalDateTime closeDate = dto.getCloseDate().toLocalDateTime();

        if (publicDate == null || closeDate == null) {
            return true;
        }

        if (!(publicDate instanceof LocalDateTime)
                || !(closeDate instanceof LocalDateTime)) {
            throw new IllegalArgumentException(
                    "Illegal method signature, expected two parameters of type LocalDate.");
        }
        System.out.println(publicDate);
        System.out.println(closeDate);
        return (publicDate).isAfter(LocalDateTime.now())
                && (publicDate).isBefore(closeDate);
    }


}
