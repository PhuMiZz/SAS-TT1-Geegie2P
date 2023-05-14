package sit.int221.announcementsystem.validDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sit.int221.announcementsystem.dtos.AnnouncementCreateUpdateDto;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;


public class CloseDateValidator implements ConstraintValidator<ValidCloseDate, AnnouncementCreateUpdateDto> {
    @Override
    public boolean isValid(AnnouncementCreateUpdateDto dto, ConstraintValidatorContext context) {
        if (dto.getPublishDate() == null || dto.getCloseDate() == null) {
            return true;
        }
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        boolean isFutureDate = dto.getCloseDate().isAfter(now);
        boolean isAfterPublishDate = dto.getCloseDate().isAfter(dto.getPublishDate());

        if (!isAfterPublishDate) {
            System.out.println("must be later than publish date");
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("must be later than publish date")
                    .addPropertyNode("closeDate")
                    .addConstraintViolation();
            return false;
        }

        else if (!isFutureDate) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("must be a future date")
                    .addPropertyNode("closeDate")
                    .addConstraintViolation();
            System.out.println("must be a future date");
            return false;
        }


        return true;
    }
}
