package med.voll.api.domain.security.autenticacao;

import org.springframework.security.core.GrantedAuthority;

public enum Autoridades implements GrantedAuthority {
    ROLE_MEDICO,
    ROLE_PACIENTE,
    ROLE_RH,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
