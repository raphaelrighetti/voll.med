package med.voll.api.dto.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.dto.endereco.EnderecoDTO;
import med.voll.api.entity.medico.Especialidade;

public record MedicoDTO(
        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank @Pattern(regexp = "\\d{2}9\\d{8}")
        String telefone,
        @NotBlank @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull @Valid
        EnderecoDTO endereco
    ) {
}
