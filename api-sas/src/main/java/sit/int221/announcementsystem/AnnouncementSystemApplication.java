package sit.int221.announcementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class AnnouncementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnnouncementSystemApplication.class, args);
    }


}
