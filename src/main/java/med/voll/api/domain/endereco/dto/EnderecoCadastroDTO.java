package med.voll.api.domain.endereco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoCadastroDTO(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank @Pattern(regexp = "[A-Z]{2}", message = "Precisa conter apenas duas letras, seguindo o padrão de UF")
        String uf,
        @NotBlank @Pattern(regexp = "\\d{8}", message = "Precisa ser um cep no formato válido")
        String cep,
        Integer numero,
        String complemento
) {
}
