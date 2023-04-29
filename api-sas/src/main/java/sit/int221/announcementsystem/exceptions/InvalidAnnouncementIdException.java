package sit.int221.announcementsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidAnnouncementIdException extends RuntimeException{
    public InvalidAnnouncementIdException(String message){
        super(message);
    }
}
