package med.voll.api.domain.consulta;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	@Query("""
			select c from Consulta c
			where c.medico.id = :medicoId
			and c.data = :data
			""")
	Consulta consultaDoMedicoNoDiaHorario(Long medicoId, LocalDateTime data);
	
	@Query("""
			select c from Consulta c
			where c.medico.id = :medicoId
			and c.data > current_timestamp
			and c.status = DISPONIVEL
			""")
	List<Consulta> consultasDisponiveisDoMedico(Long medicoId);
	
	@Query("""
			select c from Consulta c
			where c.data > current_timestamp
			and c.status = DISPONIVEL
			""")
	List<Consulta> consultasDisponiveis();
	
	@Query(value = """
			select * from consulta
			where paciente_id = :pacienteId
			and DATE(data) = DATE(:data)
			and status != 'DISPONIVEL'
			and status != 'CANCELADA'
			""", nativeQuery = true)
	Consulta consultaDoPacienteNoDia(Long pacienteId, LocalDateTime data);
}
