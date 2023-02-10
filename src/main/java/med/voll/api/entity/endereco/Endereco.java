package med.voll.api.entity.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.endereco.EnderecoDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private Integer numero;
    private String complemento;

    public Endereco(EnderecoDTO dto) {
        logradouro = dto.logradouro();
        bairro = dto.bairro();
        cep = dto.cep();
        cidade = dto.cidade();
        uf = dto.uf();
        numero = dto.numero();
        complemento = dto.complemento();
    }
}
