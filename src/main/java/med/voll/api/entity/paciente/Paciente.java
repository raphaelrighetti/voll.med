package med.voll.api.entity.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.paciente.PacienteDTOCadastro;
import med.voll.api.entity.endereco.Endereco;

@Table(name = "paciente")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    @Embedded
    private Endereco endereco;

    public Paciente(PacienteDTOCadastro dto) {
        nome = dto.nome();
        email = dto.email();
        cpf = dto.cpf();
        telefone = dto.telefone();
        endereco = new Endereco(dto.endereco());
    }
}
