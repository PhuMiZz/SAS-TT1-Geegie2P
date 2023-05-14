package sit.int221.announcementsystem.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import sit.int221.announcementsystem.validDate.ValidCloseDate;
import sit.int221.announcementsystem.validDate.ValidPublishDate;


import java.time.ZonedDateTime;
@Data
@Validated
@ValidCloseDate
public class AnnouncementCreateUpdateDto {
    private int id;
    @NotNull(message = "must not be null")
    @NotEmpty(message = "must not be blank")
    @Size(min = 1,max = 200, message = "size must be between 1 and 200")
    private String announcementTitle;

    @NotNull(message = "must not be null")
    @NotEmpty(message = "must not be blank")
    @Size(min = 1,max = 10000, message = "size must be between 1 and 10000")
    private String announcementDescription;

    @NotNull(message = "must not be null")
    private Integer categoryId;


    @ValidPublishDate
    private ZonedDateTime publishDate;


    private ZonedDateTime closeDate;

    @NotEmpty(message = "must be either 'Y' or 'N'")
    private String announcementDisplay;

}
