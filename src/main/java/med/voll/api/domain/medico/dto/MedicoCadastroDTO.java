package med.voll.api.domain.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.dto.EnderecoCadastroDTO;
import med.voll.api.domain.medico.entity.Especialidade;

public record MedicoCadastroDTO(
        @NotBlank
        String nome,
        @NotBlank @Email
        String email,
        @NotBlank
        String senha,
        @NotBlank @Pattern(regexp = "\\d{2}9\\d{8}", message = "Precisa ser um número de telefone celular válido")
        String telefone,
        @NotBlank @Pattern(regexp = "\\d{4,6}", message = "Precisa ser um CRM válido")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull @Valid
        EnderecoCadastroDTO endereco
) {
}
