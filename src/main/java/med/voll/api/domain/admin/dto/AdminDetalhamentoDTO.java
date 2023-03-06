package med.voll.api.domain.admin.dto;

import med.voll.api.domain.admin.Admin;
import med.voll.api.domain.endereco.dto.EnderecoDetalhamentoDTO;
import med.voll.api.domain.paciente.Paciente;

public record AdminDetalhamentoDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        EnderecoDetalhamentoDTO endereco
) {
    public AdminDetalhamentoDTO(Admin admin) {
        this(
                admin.getId(),
                admin.getNome(),
                admin.getUsuario().getEmail(),
                admin.getTelefone(),
                admin.getCpf(),
                new EnderecoDetalhamentoDTO(admin.getEndereco())
        );
    }
}
