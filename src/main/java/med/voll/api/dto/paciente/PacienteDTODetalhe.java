package med.voll.api.dto.paciente;

import med.voll.api.dto.endereco.EnderecoDTODetalhe;
import med.voll.api.entity.paciente.Paciente;

public record PacienteDTODetalhe(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        EnderecoDTODetalhe endereco,
        Boolean status
) {
    public PacienteDTODetalhe(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf(),
                paciente.getTelefone(),
                new EnderecoDTODetalhe(paciente.getEndereco()),
                paciente.getStatus());
    }
}
