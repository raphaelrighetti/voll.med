package med.voll.api.dto.medico;

import med.voll.api.entity.medico.Especialidade;
import med.voll.api.entity.medico.Medico;

public record MedicoDTOListagem(
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {

    public MedicoDTOListagem(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
