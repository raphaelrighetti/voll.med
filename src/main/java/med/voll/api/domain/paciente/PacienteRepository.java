package med.voll.api.domain.paciente;

import med.voll.api.domain.paciente.dto.PacienteListagemDTO;
import med.voll.api.domain.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("""
            select p from Paciente p
            where p.usuario.ativo = true
            """)
    Page<PacienteListagemDTO> findPacientesAtivos(Pageable pageable);
}
