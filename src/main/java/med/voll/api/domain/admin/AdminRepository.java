package med.voll.api.domain.admin;

import med.voll.api.domain.admin.dto.AdminListagemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("""
            select a from Admin a
            where a.usuario.ativo = true
            """)
    Page<AdminListagemDTO> findAdminsAtivos(Pageable pageable);
}
