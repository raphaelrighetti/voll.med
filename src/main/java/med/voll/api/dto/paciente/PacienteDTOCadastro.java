package med.voll.api.dto.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.dto.endereco.EnderecoDTOCadastro;

public record PacienteDTOCadastro(
        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotBlank @Pattern(regexp = "\\d{2}9\\d{8}")
        String telefone,
        @NotNull @Valid
        EnderecoDTOCadastro endereco
) {
}
