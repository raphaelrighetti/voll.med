package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.dto.EnderecoAtualizacaoDTO;
import med.voll.api.domain.endereco.dto.EnderecoCadastroDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;

    private String bairro;

    private String cidade;

    private String uf;

    private String cep;

    private Integer numero;

    private String complemento;

    public Endereco(EnderecoCadastroDTO dados) {
        logradouro = dados.logradouro();
        bairro = dados.bairro();
        cidade = dados.cidade();
        uf = dados.uf();
        cep = dados.cep();
        numero = dados.numero();
        complemento = dados.complemento();
    }

    public void atualizar(EnderecoAtualizacaoDTO dados) {
        if (dados.logradouro() != null) logradouro = dados.logradouro();
        if (dados.bairro() != null) bairro = dados.bairro();
        if (dados.cidade() != null) cidade = dados.cidade();
        if (dados.uf() != null) uf = dados.uf();
        if (dados.cep() != null) cep = dados.cep();
        if (dados.numero() != null) numero = dados.numero();
        if (dados.complemento() != null) complemento = dados.complemento();
    }
}
