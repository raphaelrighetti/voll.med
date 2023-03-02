package med.voll.api.domain.medico.dto;

import med.voll.api.domain.endereco.dto.EnderecoDetalhamentoDTO;
import med.voll.api.domain.medico.entity.Especialidade;
import med.voll.api.domain.medico.entity.Medico;
import med.voll.api.domain.security.autenticacao.Autoridades;

public record MedicoDetalhamentoDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        EnderecoDetalhamentoDTO endereco,
        Boolean ativo,
        Autoridades autoridade
) {
    public MedicoDetalhamentoDTO(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getUsuario().getEmail(),
                medico.getTelefone(),
                medico.getCrm(),
                medico.getEspecialidade(),
                new EnderecoDetalhamentoDTO(medico.getEndereco()),
                medico.getUsuario().getAtivo(),
                medico.getUsuario().getAutoridade()
        );
    }
}
