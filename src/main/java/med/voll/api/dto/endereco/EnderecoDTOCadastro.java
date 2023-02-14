package med.voll.api.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTOCadastro(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        Integer numero,
        String complemento
    ) {
}
