package sit.int221.announcementsystem.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sit.int221.announcementsystem.dtos.AnnouncementCreateUpdateDto;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;


public class CloseDateValidator implements ConstraintValidator<ValidCloseDate, AnnouncementCreateUpdateDto> {
    @Override
    public boolean isValid(AnnouncementCreateUpdateDto dto, ConstraintValidatorContext context) {
        if (dto.getCloseDate() == null) {
            return true;
        }

        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        if (!isFutureDate(dto.getCloseDate(), now)) {
            addConstraintViolation(context, "must be a future date");
            return false;
        }

        if (dto.getPublishDate() != null && !isAfterPublishDate(dto.getCloseDate(), dto.getPublishDate())) {
            addConstraintViolation(context, "must be later than publish date");
            return false;
        }

        return true;
    }

    private boolean isFutureDate(ZonedDateTime dateToCheck, ZonedDateTime referenceDate) {
        return dateToCheck.isAfter(referenceDate);
    }

    private boolean isAfterPublishDate(ZonedDateTime closeDate, ZonedDateTime publishDate) {
        return closeDate.isAfter(publishDate);
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode("closeDate")
                .addConstraintViolation();
    }
}
