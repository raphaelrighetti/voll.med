package med.voll.api.domain.genericos.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import med.voll.api.domain.genericos.annotation.validator.CrmValidator;
import med.voll.api.domain.genericos.annotation.validator.TelefoneValidator;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CrmValidator.class)
@Documented
public @interface Crm {

    String message() default "Precisa ser um CRM válido";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
