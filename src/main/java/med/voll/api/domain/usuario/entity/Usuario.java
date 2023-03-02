package med.voll.api.domain.usuario.entity;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.medico.dto.MedicoCadastroDTO;
import med.voll.api.domain.security.autenticacao.Autoridades;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String senha;

    private Boolean ativo = true;

    private Autoridades autoridade;

    public Usuario(MedicoCadastroDTO dados) {
        email = dados.email();
        autoridade = Autoridades.ROLE_MEDICO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(autoridade);
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ativo;
    }
}
