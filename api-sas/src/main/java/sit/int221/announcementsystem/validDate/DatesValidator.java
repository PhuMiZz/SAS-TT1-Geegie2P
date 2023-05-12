package sit.int221.announcementsystem.validDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sit.int221.announcementsystem.dtos.AnnouncementCreateUpdateDto;

public class DatesValidator implements ConstraintValidator<ValidDates, AnnouncementCreateUpdateDto> {
    @Override
    public boolean isValid(AnnouncementCreateUpdateDto dto, ConstraintValidatorContext context) {
        if (dto.getPublishDate() == null || dto.getCloseDate() == null) {
            return true;
        }

        boolean valid = dto.getCloseDate().isAfter(dto.getPublishDate());

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Close date must be after publish date")
                    .addPropertyNode("closeDate")
                    .addConstraintViolation();
        }

        return valid;
    }
}
