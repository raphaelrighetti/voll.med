package med.voll.api.domain.rh.dto;

import med.voll.api.domain.admin.Admin;
import med.voll.api.domain.endereco.dto.EnderecoDetalhamentoDTO;
import med.voll.api.domain.rh.FuncionarioRH;

public record FuncionarioRHDetalhamentoDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        EnderecoDetalhamentoDTO endereco
) {
    public FuncionarioRHDetalhamentoDTO(FuncionarioRH funcionarioRH) {
        this(
                funcionarioRH.getId(),
                funcionarioRH.getNome(),
                funcionarioRH.getUsuario().getEmail(),
                funcionarioRH.getTelefone(),
                funcionarioRH.getCpf(),
                new EnderecoDetalhamentoDTO(funcionarioRH.getEndereco())
        );
    }
}
