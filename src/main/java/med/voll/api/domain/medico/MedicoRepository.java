package med.voll.api.domain.medico;

import med.voll.api.domain.medico.dto.MedicoListagemDTO;

import java.time.LocalDateTime;
import java.util.List;

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
    
    @Query("""
    		select c.data from Consulta c
    		where c.medico.id = :id
    		and c.status != CANCELADA
    		""")
    List<LocalDateTime> horariosOcupadosDoMedico(Long id);
}
