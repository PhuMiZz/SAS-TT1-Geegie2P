package sit.int221.announcementsystem.exceptions;


public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
