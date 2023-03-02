package med.voll.api.domain.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioAutenticacaoDTO(
        @NotBlank @Email
        String email,
        @NotBlank
        String senha
) {
}
