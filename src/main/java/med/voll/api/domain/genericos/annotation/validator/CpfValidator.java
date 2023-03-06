package med.voll.api.domain.genericos.annotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import med.voll.api.domain.genericos.annotation.Cpf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CpfValidator implements ConstraintValidator<Cpf, String> {

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile("\\d{11}");
        Matcher matcher = pattern.matcher(valor);

        try {
            return matcher.matches();
        } catch (Exception e) {
            return false;
        }
    }
}
