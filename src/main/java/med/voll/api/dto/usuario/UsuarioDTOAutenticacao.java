package med.voll.api.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTOAutenticacao(
        @NotBlank
        String login,
        @NotBlank
        String senha
) {
}
