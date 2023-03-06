package med.voll.api.domain.endereco.dto;

import med.voll.api.domain.endereco.Endereco;

public record EnderecoDetalhamentoDTO(
        String logradouro,
        String bairro,
        String cidade,
        String uf,
        String cep,
        Integer numero,
        String complemento
) {
    public EnderecoDetalhamentoDTO(Endereco endereco) {
        this(
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getUf(),
                endereco.getCep(),
                endereco.getNumero(),
                endereco.getComplemento()
        );
    }
}
