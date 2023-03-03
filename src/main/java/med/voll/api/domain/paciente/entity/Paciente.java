package med.voll.api.domain.paciente.entity;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.endereco.entity.Endereco;
import med.voll.api.domain.paciente.dto.PacienteAtualizacaoDTO;
import med.voll.api.domain.paciente.dto.PacienteCadastroDTO;
import med.voll.api.domain.usuario.entity.Usuario;

@Table(name = "paciente")
@Entity(name = "Paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "cpf"})
public class Paciente {

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

    public Paciente(PacienteCadastroDTO dados) {
        nome = dados.nome();
        telefone = dados.telefone();
        cpf = dados.cpf();
        endereco = new Endereco(dados.endereco());
    }

    public void atualizar(PacienteAtualizacaoDTO dados) {
        if (dados.nome() != null) nome = dados.nome();
        if (dados.telefone() != null) telefone = dados.telefone();
        if (dados.endereco() != null) endereco.atualizar(dados.endereco());
    }

    public void inativar() {
        usuario.setAtivo(false);
    }
}
