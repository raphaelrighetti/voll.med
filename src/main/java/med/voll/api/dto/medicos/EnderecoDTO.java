package med.voll.api.dto.medicos;

public record EnderecoDTO(
        String logradouro,
        String bairro,
        String cep,
        String cidade,
        String uf,
        Integer numero,
        String complemento
    ) {
}
