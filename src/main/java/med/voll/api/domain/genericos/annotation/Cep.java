package med.voll.api.domain.genericos.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import med.voll.api.domain.genericos.annotation.validator.CepValidator;
import med.voll.api.domain.genericos.annotation.validator.TelefoneValidator;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CepValidator.class)
@Documented
public @interface Cep {

    String message() default "Precisa ser um CEP no formato válido";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
