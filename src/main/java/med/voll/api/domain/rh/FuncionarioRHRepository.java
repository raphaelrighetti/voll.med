package med.voll.api.domain.rh;

import med.voll.api.domain.rh.dto.FuncionarioRHListagemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FuncionarioRHRepository extends JpaRepository<FuncionarioRH, Long> {

    @Query("""
            select f from FuncionarioRH f
            where f.usuario.ativo = true
            """)
    Page<FuncionarioRHListagemDTO> findFuncionariosAtivos(Pageable pageable);
}
