package med.voll.api.domain.paciente.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.dto.EnderecoCadastroDTO;

public record PacienteCadastroDTO(
        @NotBlank
        String nome,
        @NotBlank @Email
        String email,
        @NotBlank
        String senha,
        @NotBlank @Pattern(regexp = "\\d{2}9\\d{8}", message = "Precisa ser um número de telefone celular válido")
        String telefone,
        @NotBlank @Pattern(regexp = "\\d{11}", message = "Precisa ser um CPF válido")
        String cpf,
        @NotNull @Valid
        EnderecoCadastroDTO endereco
) {
}
