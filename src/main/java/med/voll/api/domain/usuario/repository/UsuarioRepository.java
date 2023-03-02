package med.voll.api.domain.usuario.repository;

import med.voll.api.domain.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}
