package med.voll.api.dto.usuario;

import med.voll.api.entity.usuario.Usuario;

public record UsuarioDTODetalhamento(Long id, String login) {
    public UsuarioDTODetalhamento(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin());
    }
}
