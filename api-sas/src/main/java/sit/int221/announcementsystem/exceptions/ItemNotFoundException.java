package sit.int221.announcementsystem.exceptions;


public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String message){
        super(message);
    }
}
