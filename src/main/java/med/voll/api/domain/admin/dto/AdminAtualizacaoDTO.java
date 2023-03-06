package med.voll.api.domain.admin.dto;

import jakarta.validation.Valid;
import med.voll.api.domain.endereco.dto.EnderecoAtualizacaoDTO;
import med.voll.api.domain.genericos.annotation.Telefone;

public record AdminAtualizacaoDTO(
        String nome,
        @Telefone
        String telefone,
        @Valid
        EnderecoAtualizacaoDTO endereco
) {
}
