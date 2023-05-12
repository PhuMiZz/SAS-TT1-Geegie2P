package sit.int221.announcementsystem.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import sit.int221.announcementsystem.annotations.DateTimeConstraints;

import java.time.ZonedDateTime;

@Data
@DateTimeConstraints
@Validated
public class AnnouncementCreateUpdateDto {
    private int id;
    @NotBlank() @Size(min = 1, max = 200)
    private String announcementTitle;
    @NotBlank() @Size(min = 1, max = 10000)
    private String announcementDescription;
    private int categoryId;


    private ZonedDateTime publishDate;

    private ZonedDateTime closeDate;

    private String announcementDisplay;



}
