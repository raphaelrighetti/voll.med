package med.voll.api.domain.genericos.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import med.voll.api.domain.genericos.annotation.validator.TelefoneValidator;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TelefoneValidator.class)
@Documented
public @interface Telefone {

    String message() default "Precisa ser um número de telefone celular válido";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
