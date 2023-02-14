package med.voll.api.entity.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.endereco.EnderecoDTOAtualizacao;
import med.voll.api.dto.endereco.EnderecoDTOCadastro;

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

    public Endereco(EnderecoDTOCadastro dto) {
        logradouro = dto.logradouro();
        bairro = dto.bairro();
        cep = dto.cep();
        cidade = dto.cidade();
        uf = dto.uf();
        numero = dto.numero();
        complemento = dto.complemento();
    }

    public void atualizarCampos(EnderecoDTOAtualizacao dados) {
        if (dados.logradouro() != null) logradouro = dados.logradouro();
        if (dados.bairro() != null) bairro = dados.bairro();
        if (dados.cep() != null) cep = dados.cep();
        if (dados.cidade() != null) cidade = dados.cidade();
        if (dados.uf() != null) uf = dados.uf();
        if (dados.numero() != null) numero = dados.numero();
        if (dados.complemento() != null) complemento = dados.complemento();
    }
}
