package med.voll.api.dto.paciente;

import med.voll.api.entity.paciente.Paciente;

public record PacienteDTOListagem(
        String nome,
        String email,
        String cpf
) {
    public PacienteDTOListagem(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
