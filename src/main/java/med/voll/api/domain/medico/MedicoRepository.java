package med.voll.api.domain.medico;

import med.voll.api.domain.medico.dto.MedicoListagemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("""
            select m from Medico m
            where m.usuario.ativo = true
            """)
    Page<MedicoListagemDTO> findMedicosAtivos(Pageable pageable);
}
