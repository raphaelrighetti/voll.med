package med.voll.api.domain.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.dto.EnderecoAtualizacaoDTO;
import med.voll.api.domain.genericos.annotation.Telefone;

public record MedicoAtualizacaoDTO(
        String nome,
        @Telefone
        String telefone,
        @Valid
        EnderecoAtualizacaoDTO endereco
) {
}
