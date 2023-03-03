package med.voll.api.domain.paciente.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.dto.EnderecoAtualizacaoDTO;

public record PacienteAtualizacaoDTO(
        String nome,
        @Pattern(regexp = "\\d{2}9\\d{8}", message = "Precisa ser um número de telefone celular válido")
        String telefone,
        @Valid
        EnderecoAtualizacaoDTO endereco
) {
}
