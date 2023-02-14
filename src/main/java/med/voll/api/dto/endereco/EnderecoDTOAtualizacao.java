package med.voll.api.dto.endereco;

import jakarta.validation.constraints.Pattern;

public record EnderecoDTOAtualizacao(
        String logradouro,
        String bairro,
        @Pattern(regexp = "\\d{8}")
        String cep,
        String cidade,
        String uf,
        Integer numero,
        String complemento
) {
}
