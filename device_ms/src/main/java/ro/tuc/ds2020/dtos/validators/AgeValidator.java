package ro.tuc.ds2020.dtos.validators;

import org.springframework.stereotype.Component;
import ro.tuc.ds2020.dtos.validators.annotation.ConsumptionLimit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class AgeValidator implements ConstraintValidator<ConsumptionLimit, Integer> {

    private int ageLimit;

    @Override
    public void initialize(ConsumptionLimit constraintAnnotation) {
        this.ageLimit = constraintAnnotation.limit();
    }

    @Override
    public boolean isValid(Integer inputAge, ConstraintValidatorContext constraintValidatorContext) {
        return inputAge > ageLimit;
    }


}
