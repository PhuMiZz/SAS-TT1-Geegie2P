package sit.int221.announcementsystem.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
public class CategoryDto {
    private int id;
    private String categoryName;

    public String getAnnouncementCategory(){
        return categoryName;
    }
}
