package med.voll.api.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTOCadastro(
        @NotBlank @Email
        String login,
        @NotBlank
        String senha
) {
}
