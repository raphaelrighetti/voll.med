package med.voll.api.domain.usuario;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.security.autorizacao.Autoridades;
import med.voll.api.domain.genericos.UsuarioCadastro;
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
@EqualsAndHashCode(of = {"id", "email"})
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String senha;

    private Boolean ativo = true;

    private Autoridades autoridade;

    public Usuario(UsuarioCadastro dados) {
        email = dados.getEmail();
        autoridade = dados.getAutoridade();
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
