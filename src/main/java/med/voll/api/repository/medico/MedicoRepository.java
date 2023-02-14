package med.voll.api.repository.medico;

import med.voll.api.dto.medico.MedicoDTOListagem;
import med.voll.api.entity.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<MedicoDTOListagem> findByStatusTrue(Pageable pageable);
}
