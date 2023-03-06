package med.voll.api.domain.genericos.annotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import med.voll.api.domain.genericos.annotation.Telefone;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelefoneValidator implements ConstraintValidator<Telefone, String> {


    @Override
    public boolean isValid(String valor, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile("\\d{2}9\\d{8}");
        Matcher matcher = pattern.matcher(valor);

        try {
            return matcher.matches();

        } catch (Exception e) {
            return false;
        }
    }
}
