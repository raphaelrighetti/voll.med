package med.voll.api.domain.admin;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.admin.dto.AdminAtualizacaoDTO;
import med.voll.api.domain.admin.dto.AdminCadastroDTO;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.usuario.Usuario;

@Table(name = "admin")
@Entity(name = "Admin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "cpf"})
public class Admin {

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

    public Admin(AdminCadastroDTO dados) {
        nome = dados.nome();
        telefone = dados.telefone();
        cpf = dados.cpf();
        endereco = new Endereco(dados.endereco());
    }

    public void atualizar(AdminAtualizacaoDTO dados) {
        if (dados.nome() != null) nome = dados.nome();
        if (dados.telefone() != null) telefone = dados.telefone();
        if (dados.endereco() != null) endereco.atualizar(dados.endereco());
    }

    public void inativar() {
        usuario.setAtivo(false);
    }
}
