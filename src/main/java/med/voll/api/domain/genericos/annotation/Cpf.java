package med.voll.api.domain.genericos.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import med.voll.api.domain.genericos.annotation.validator.CpfValidator;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CpfValidator.class)
@Documented
public @interface Cpf {

    String message() default "Precisa ser um CPF válido";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
