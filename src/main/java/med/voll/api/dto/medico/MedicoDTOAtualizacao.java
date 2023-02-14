package med.voll.api.dto.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.dto.endereco.EnderecoDTOAtualizacao;

public record MedicoDTOAtualizacao(
        @NotNull
        Long id,
        String nome,
        @Pattern(regexp = "\\d{2}9\\d{8}")
        String telefone,
        @Valid
        EnderecoDTOAtualizacao endereco) {
}
