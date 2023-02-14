package med.voll.api.entity.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.paciente.PacienteDTOAtualizacao;
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
    private Boolean status = true;

    public Paciente(PacienteDTOCadastro dto) {
        nome = dto.nome();
        email = dto.email();
        cpf = dto.cpf();
        telefone = dto.telefone();
        endereco = new Endereco(dto.endereco());
    }

    public void atualizarCampos(PacienteDTOAtualizacao dados) {
        if (dados.nome() != null) nome = dados.nome();
        if (dados.telefone() != null) telefone = dados.telefone();
        if (dados.endereco() != null) endereco.atualizarCampos(dados.endereco());
    }

    public void inativar() {
        status = false;
    }
}
