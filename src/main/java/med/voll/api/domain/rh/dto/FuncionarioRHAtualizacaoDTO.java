package med.voll.api.domain.rh.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.dto.EnderecoAtualizacaoDTO;
import med.voll.api.domain.genericos.annotation.Telefone;

public record FuncionarioRHAtualizacaoDTO(
        String nome,
        @Telefone
        String telefone,
        @Valid
        EnderecoAtualizacaoDTO endereco
) {
}
