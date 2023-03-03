package med.voll.api.domain.paciente.repository;

import med.voll.api.domain.paciente.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
