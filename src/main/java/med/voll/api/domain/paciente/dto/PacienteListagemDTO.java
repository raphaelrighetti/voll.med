package med.voll.api.domain.paciente.dto;

import med.voll.api.domain.paciente.Paciente;

public record PacienteListagemDTO(
        String nome,
        String email,
        String cpf
) {
    public PacienteListagemDTO(Paciente paciente) {
        this(paciente.getNome(), paciente.getUsuario().getEmail(), paciente.getCpf());
    }
}
