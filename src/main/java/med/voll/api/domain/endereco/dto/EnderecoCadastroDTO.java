package med.voll.api.domain.endereco.dto;

import jakarta.validation.constraints.NotBlank;
import med.voll.api.domain.genericos.annotation.Cep;
import med.voll.api.domain.genericos.annotation.Uf;

public record EnderecoCadastroDTO(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank @Uf
        String uf,
        @NotBlank @Cep
        String cep,
        Integer numero,
        String complemento
) {
}
