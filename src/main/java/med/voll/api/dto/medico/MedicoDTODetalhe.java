package med.voll.api.dto.medico;

import med.voll.api.dto.endereco.EnderecoDTODetalhe;
import med.voll.api.entity.medico.Especialidade;
import med.voll.api.entity.medico.Medico;

public record MedicoDTODetalhe(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
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
