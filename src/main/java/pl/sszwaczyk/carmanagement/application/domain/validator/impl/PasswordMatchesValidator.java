package pl.sszwaczyk.carmanagement.application.domain.validator.impl;

import pl.sszwaczyk.carmanagement.application.domain.entities.dto.UserDTO;
import pl.sszwaczyk.carmanagement.application.domain.validator.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserDTO user = (UserDTO) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
