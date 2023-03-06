package med.voll.api.domain.genericos.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import med.voll.api.domain.genericos.annotation.validator.TelefoneValidator;
import med.voll.api.domain.genericos.annotation.validator.UfValidator;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UfValidator.class)
@Documented
public @interface Uf {

    String message() default "Precisa conter apenas duas letras, seguindo o padrão de UF";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
