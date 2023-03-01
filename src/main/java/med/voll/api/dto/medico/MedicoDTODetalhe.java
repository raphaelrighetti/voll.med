package med.voll.api.dto.medico;

import med.voll.api.dto.endereco.EnderecoDTODetalhe;
import med.voll.api.entity.medico.Medico;
import med.voll.api.entity.medico.MedicoEspecialidade;

public record MedicoDTODetalhe(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        MedicoEspecialidade especialidade,
        EnderecoDTODetalhe endereco,
        Boolean status
) {
    public MedicoDTODetalhe(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getCrm(),
                medico.getEspecialidade(),
                new EnderecoDTODetalhe(medico.getEndereco()),
                medico.getStatus()
        );
    }
}
