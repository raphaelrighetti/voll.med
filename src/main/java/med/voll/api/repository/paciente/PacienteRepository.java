package med.voll.api.repository.paciente;

import med.voll.api.dto.paciente.PacienteDTOListagem;
import med.voll.api.entity.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<PacienteDTOListagem> findByStatusTrue(Pageable pageable);
}
