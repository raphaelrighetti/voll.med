package med.voll.api.domain.medico;

import med.voll.api.domain.medico.dto.MedicoListagemDTO;

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
    Page<MedicoListagemDTO> medicosAtivos(Pageable pageable);
    
    @Query("""
    		select m from Medico m
    		where m.usuario.ativo = true
    		and m.especialidade = :especialidade
    		""")
    List<MedicoListagemDTO> medicosPorEspecialidade(Especialidade especialidade);
}
