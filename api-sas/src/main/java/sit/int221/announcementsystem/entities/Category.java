package sit.int221.announcementsystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "catagories")
public class Category {
    @Id
    @Column(name = "categoryId")
    private Integer id;

    @Column(name = "categoryName")
    private String name;
}
