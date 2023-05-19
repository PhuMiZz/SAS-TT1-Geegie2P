package sit.int221.announcementsystem.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponseValidException {
    private int status;
    private String title;
    private String instance;
    private List<FieldError> detail;

    @AllArgsConstructor
    @Getter
    @Setter
    public static class FieldError {
        private String field;
        private String errorMessage;

    }
}
