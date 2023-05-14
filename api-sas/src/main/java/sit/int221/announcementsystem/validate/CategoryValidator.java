package sit.int221.announcementsystem.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import sit.int221.announcementsystem.repositories.CategoryRepository;

@Configurable(autowire = Autowire.BY_TYPE, dependencyCheck = true)
public class CategoryValidator implements ConstraintValidator<ValidCategory, Integer> {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public boolean isValid(Integer categoryId, ConstraintValidatorContext context) {
        try {
            return categoryRepository.existsById(categoryId);
        }catch (Exception ex){
            return false;
        }
    }
}
