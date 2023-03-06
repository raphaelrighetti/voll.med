package med.voll.api.domain.genericos;

import med.voll.api.domain.security.autorizacao.Autoridades;

public interface UsuarioCadastro {

    String getEmail();

    Autoridades getAutoridade();
}
