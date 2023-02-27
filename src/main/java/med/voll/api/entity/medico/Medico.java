package med.voll.api.entity.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.medico.MedicoDTOAtualizacao;
import med.voll.api.dto.medico.MedicoDTOCadastro;
import med.voll.api.entity.endereco.Endereco;

@Table(name = "medico")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private MedicoEspecialidade especialidade;
    @Embedded
    private Endereco endereco;
    private Boolean status = true;

    public Medico(MedicoDTOCadastro dto) {
        nome = dto.nome();
        email = dto.email();
        telefone = dto.telefone();
        crm = dto.crm();
        especialidade = dto.especialidade();
        endereco = new Endereco(dto.endereco());
    }

    public void atualizarCampos(MedicoDTOAtualizacao dados) {
        if (dados.nome() != null) nome = dados.nome();
        if (dados.telefone() != null) telefone = dados.telefone();
        if (dados.endereco() != null) endereco.atualizarCampos(dados.endereco());
    }

    public void inativar() {
        status = false;
    }
}
