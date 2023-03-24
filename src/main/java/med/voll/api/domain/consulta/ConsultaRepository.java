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
			where c.data > :agora
			and c.status = DISPONIVEL
			""")
	List<Consulta> consultasDisponiveis(LocalDateTime agora);
	
	@Query("""
			select c from Consulta c
			where c.medico.id = :medicoId
			and c.data > :agora
			and c.status = DISPONIVEL
			""")
	List<Consulta> consultasDisponiveisDoMedico(Long medicoId, LocalDateTime agora);
	
	@Query("""
			select c from Consulta c
			where c.data > :agora
			and c.status = AGENDADA
			""")
	List<Consulta> consultasAgendadas(LocalDateTime agora);
	
	@Query("""
			select c from Consulta c
			where c.medico.id = :medicoId
			and c.data > :agora
			and c.status = AGENDADA
			""")
	List<Consulta> consultasAgendadasDoMedico(Long medicoId, LocalDateTime agora);
	
	@Query("""
			select c from Consulta c
			where c.paciente.id = :pacienteId
			and c.data > :agora
			and c.status = AGENDADA
			""")
	List<Consulta> consultasAgendadasDoPaciente(Long pacienteId, LocalDateTime agora);
	
	@Query("""
			select c from Consulta c
			where c.status = FINALIZADA
			""")
	List<Consulta> consultasFinalizadas();
	
	@Query("""
			select c from Consulta c
			where c.medico.id = :medicoId
			and c.status != FINALIZADA
			""")
	List<Consulta> consultasFinalizadasDoMedico(Long medicoId);
	
	@Query("""
			select c from Consulta c
			where c.paciente.id = :pacienteId
			and c.status != FINALIZADA
			""")
	List<Consulta> consultasFinalizadasDoPaciente(Long pacienteId);
	
	@Query(value = """
			select * from consulta
			where paciente_id = :pacienteId
			and DATE(data) = DATE(:data)
			and status != 'DISPONIVEL'
			""", nativeQuery = true)
	Consulta consultaDoPacienteNoDia(Long pacienteId, LocalDateTime data);
}
