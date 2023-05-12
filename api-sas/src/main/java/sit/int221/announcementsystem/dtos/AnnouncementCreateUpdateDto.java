package sit.int221.announcementsystem.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import sit.int221.announcementsystem.validDate.ValidCloseDate;


import java.time.ZonedDateTime;
@Data
@Validated
@ValidCloseDate
public class AnnouncementCreateUpdateDto {
    private int id;
    @Size(min = 1,max = 200, message = "Title size must be between 1 and 200")
    private String announcementTitle;
    @NotEmpty(message = "Description must not be blank")
    @Size(min = 1,max = 10000, message = "Description size must be between 1 and 10000")
    private String announcementDescription;

    @NotNull(message = "categoryId must not be null")
    private int categoryId;

    @FutureOrPresent(message = "publishDate must be in the future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime publishDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime closeDate;

    private String announcementDisplay;

}
