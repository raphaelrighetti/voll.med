package med.voll.api.domain.admin.dto;

import med.voll.api.domain.admin.Admin;
import med.voll.api.domain.paciente.Paciente;

public record AdminListagemDTO(
        String nome,
        String email,
        String cpf
) {
    public AdminListagemDTO(Admin admin) {
        this(admin.getNome(), admin.getUsuario().getEmail(), admin.getCpf());
    }
}
