package med.voll.api.domain.paciente.dto;

import med.voll.api.domain.endereco.dto.EnderecoDetalhamentoDTO;
import med.voll.api.domain.paciente.entity.Paciente;

public record PacienteDetalhamentoDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        EnderecoDetalhamentoDTO endereco
) {
    public PacienteDetalhamentoDTO(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getUsuario().getEmail(),
                paciente.getTelefone(),
                paciente.getCpf(),
                new EnderecoDetalhamentoDTO(paciente.getEndereco())
        );
    }
}
