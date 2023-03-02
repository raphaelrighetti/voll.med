package med.voll.api.domain.medico.entity;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.endereco.entity.Endereco;
import med.voll.api.domain.medico.dto.MedicoAtualizacaoDTO;
import med.voll.api.domain.medico.dto.MedicoCadastroDTO;
import med.voll.api.domain.usuario.entity.Usuario;

@Table(name = "medico")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "email"})
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String telefone;

    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(MedicoCadastroDTO dados) {
        nome = dados.nome();
        telefone = dados.telefone();
        crm = dados.crm();
        especialidade = dados.especialidade();
        endereco = new Endereco(dados.endereco());
    }

    public void atualizar(MedicoAtualizacaoDTO dados) {
        if (dados.nome() != null) nome = dados.nome();
        if (dados.telefone() != null) telefone = dados.telefone();
        if (dados.endereco() != null) endereco.atualizar(dados.endereco());
    }

    public void inativar() {
        usuario.setAtivo(false);
    }
}
