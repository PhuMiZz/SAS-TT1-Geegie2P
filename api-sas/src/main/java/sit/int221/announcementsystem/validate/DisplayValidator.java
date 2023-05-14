package sit.int221.announcementsystem.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class DisplayValidator implements ConstraintValidator<ValidDisplay, String> {
    private Pattern pattern;
    @Override
    public void initialize(ValidDisplay annotation) {
        try {
            pattern = Pattern.compile(annotation.regexp());
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Given regex is invalid", e);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        Matcher m = pattern.matcher(value);
        return m.matches();
    }

}
