package med.voll.api.dto.endereco;

import med.voll.api.entity.endereco.Endereco;

public record EnderecoDTODetalhe(
        String logradouro,
        String bairro,
        String cep,
        String cidade,
        String uf,
        Integer numero,
        String complemento
) {
    public EnderecoDTODetalhe(Endereco endereco) {
        this(endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getUf(),
                endereco.getNumero(),
                endereco.getComplemento());
    }
}
