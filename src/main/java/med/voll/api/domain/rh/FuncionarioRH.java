package med.voll.api.domain.rh;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.admin.dto.AdminAtualizacaoDTO;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.rh.dto.FuncionarioRHAtualizacaoDTO;
import med.voll.api.domain.rh.dto.FuncionarioRHCadastroDTO;
import med.voll.api.domain.usuario.Usuario;

@Table(name = "funcionario_rh")
@Entity(name = "FuncionarioRH")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "cpf"})
public class FuncionarioRH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String telefone;

    private String cpf;

    @Embedded
    private Endereco endereco;

    public FuncionarioRH(FuncionarioRHCadastroDTO dados) {
        nome = dados.nome();
        telefone = dados.telefone();
        cpf = dados.cpf();
        endereco = new Endereco(dados.endereco());
    }

    public void atualizar(FuncionarioRHAtualizacaoDTO dados) {
        if (dados.nome() != null) nome = dados.nome();
        if (dados.telefone() != null) telefone = dados.telefone();
        if (dados.endereco() != null) endereco.atualizar(dados.endereco());
    }

    public void inativar() {
        usuario.setAtivo(false);
    }
}
