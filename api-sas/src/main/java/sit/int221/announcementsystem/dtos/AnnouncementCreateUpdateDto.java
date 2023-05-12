package sit.int221.announcementsystem.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import sit.int221.announcementsystem.annotations.DateTimeConstraints;

import java.time.ZonedDateTime;

@Data
@DateTimeConstraints
@Validated
public class AnnouncementCreateUpdateDto {
    private int id;
    @Size(min = 1,max = 200, message = "Title size must be between 1 and 200")
    private String announcementTitle;
    @NotEmpty(message = "Description must not be blank")
    @Size(min = 1,max = 10000, message = "Description size must be between 1 and 10000")
    private String announcementDescription;

    @NotNull(message = "categoryId must not be null")
    private int categoryId;


    private ZonedDateTime publishDate;

    private ZonedDateTime closeDate;

    private String announcementDisplay;

    // @Future(message = "publishDate must be in the future")
    // private ZonedDateTime publishDate;
    // @Past(message = "closeDate must be a future date")
    // private ZonedDateTime closeDate;

    // private String announcementDisplay;
}
