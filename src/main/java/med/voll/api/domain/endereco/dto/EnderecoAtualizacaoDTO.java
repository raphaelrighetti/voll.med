package med.voll.api.domain.endereco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.genericos.annotation.Cep;
import med.voll.api.domain.genericos.annotation.Uf;

public record EnderecoAtualizacaoDTO(
        String logradouro,
        String bairro,
        String cidade,
        @Uf
        String uf,
        @Cep
        String cep,
        Integer numero,
        String complemento
) {
}
