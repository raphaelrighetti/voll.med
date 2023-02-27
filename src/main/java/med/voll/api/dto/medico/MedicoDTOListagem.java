package med.voll.api.dto.medico;

import med.voll.api.entity.medico.MedicoEspecialidade;
import med.voll.api.entity.medico.Medico;

public record MedicoDTOListagem(
        Long id,
        String nome,
        String email,
        String crm,
        MedicoEspecialidade especialidade
) {

    public MedicoDTOListagem(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
