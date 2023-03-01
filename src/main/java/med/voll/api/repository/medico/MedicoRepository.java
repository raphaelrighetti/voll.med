package med.voll.api.repository.medico;

import med.voll.api.dto.medico.MedicoDTOListagem;
import med.voll.api.entity.medico.Medico;
import med.voll.api.entity.medico.MedicoEspecialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<MedicoDTOListagem> findByStatusTrue(Pageable pageable);
    @Query("""
                select m from Medico m
                where m.status = 1
                and m.especialidade = :especialidade
                and m.id not in(
                        select c.medico.id from Consulta c
                        where c.data = :data
                        and c.status != CANCELADA
                )
                order by rand()
                limit 1
                """)
    Medico escolherMedicoPorEspecialidadeEDataDisponivel(MedicoEspecialidade especialidade, LocalDateTime data);
}
