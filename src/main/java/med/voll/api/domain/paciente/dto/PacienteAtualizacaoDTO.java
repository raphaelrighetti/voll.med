package med.voll.api.domain.paciente.dto;

import jakarta.validation.Valid;
import med.voll.api.domain.endereco.dto.EnderecoAtualizacaoDTO;
import med.voll.api.domain.genericos.annotation.Telefone;

public record PacienteAtualizacaoDTO(
        String nome,
        @Telefone
        String telefone,
        @Valid
        EnderecoAtualizacaoDTO endereco
) {
}
