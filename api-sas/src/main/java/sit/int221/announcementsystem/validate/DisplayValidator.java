package sit.int221.announcementsystem.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DisplayValidator  implements ConstraintValidator<ValidDisplay, String> {
    @Override
    public boolean isValid(String display, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
