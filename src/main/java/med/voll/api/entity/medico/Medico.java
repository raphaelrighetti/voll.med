package med.voll.api.entity.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.medico.MedicoDTO;
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
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    public Medico(MedicoDTO dto) {
        nome = dto.nome();
        email = dto.email();
        telefone = dto.telefone();
        crm = dto.crm();
        especialidade = dto.especialidade();
        endereco = new Endereco(dto.endereco());
    }
}
