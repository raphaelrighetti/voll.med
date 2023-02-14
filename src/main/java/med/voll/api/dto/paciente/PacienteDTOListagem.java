package med.voll.api.dto.paciente;

import med.voll.api.entity.paciente.Paciente;

public record PacienteDTOListagem(
        Long id,
        String nome,
        String email,
        String cpf
) {
    public PacienteDTOListagem(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
