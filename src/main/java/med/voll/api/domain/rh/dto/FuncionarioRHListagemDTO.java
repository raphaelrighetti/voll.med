package med.voll.api.domain.rh.dto;

import med.voll.api.domain.admin.Admin;
import med.voll.api.domain.rh.FuncionarioRH;

public record FuncionarioRHListagemDTO(
        String nome,
        String email,
        String cpf
) {
    public FuncionarioRHListagemDTO(FuncionarioRH funcionarioRH) {
        this(funcionarioRH.getNome(), funcionarioRH.getUsuario().getEmail(), funcionarioRH.getCpf());
    }
}
