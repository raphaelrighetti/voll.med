package med.voll.api.domain.medico.dto;

import med.voll.api.domain.medico.entity.Especialidade;
import med.voll.api.domain.medico.entity.Medico;

public record MedicoListagemDTO(
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public MedicoListagemDTO(Medico medico) {
        this(medico.getNome(), medico.getUsuario().getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
