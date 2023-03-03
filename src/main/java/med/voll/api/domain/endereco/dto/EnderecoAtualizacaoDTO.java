package med.voll.api.domain.endereco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoAtualizacaoDTO(
        String logradouro,
        String bairro,
        String cidade,
        @Pattern(regexp = "[A-Z]{2}", message = "Precisa conter apenas duas letras, seguindo o padrão de UF")
        String uf,
        @Pattern(regexp = "\\d{8}", message = "Precisa ser um cep no formato válido")
        String cep,
        Integer numero,
        String complemento
) {
}