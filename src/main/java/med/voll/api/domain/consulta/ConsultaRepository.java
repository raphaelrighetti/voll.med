package med.voll.api.domain.consulta;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.domain.consulta.dto.ConsultaDisponivelListagemDTO;
import med.voll.api.domain.consulta.dto.ConsultaListagemDTO;
import med.voll.api.domain.medico.Especialidade;

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
	Page<ConsultaDisponivelListagemDTO> consultasDisponiveis(LocalDateTime agora, Pageable pageable);
	
	@Query("""
			select c from Consulta c
			where c.medico.id = :medicoId
			and c.dia.id = :diaId
			and c.data > :agora
			""")
	List<ConsultaDisponivelListagemDTO> consultasDisponiveisNoDiaDoMedico(Long medicoId, Long diaId, LocalDateTime agora);
	
	@Query("""
			select c from Consulta c
			where c.medico.especialidade = :especialidade
			and c.dia.id = :diaId
			and c.data > :agora
			""")
	List<ConsultaDisponivelListagemDTO> consultasDisponiveisNoDiaPorEspecialidade(Especialidade especialidade, Long diaId, LocalDateTime agora);
	
	@Query("""
			select c from Consulta c
			where c.data > :agora
			and c.status = AGENDADA
			""")
	Page<ConsultaListagemDTO> consultasAgendadas(LocalDateTime agora, Pageable pageable);
	
	@Query("""
			select c from Consulta c
			where c.medico.id = :medicoId
			and c.data > :agora
			and c.status = AGENDADA
			""")
	Page<ConsultaListagemDTO> consultasAgendadasDoMedico(Long medicoId, LocalDateTime agora, Pageable pageable);
	
	@Query("""
			select c from Consulta c
			where c.paciente.id = :pacienteId
			and c.data > :agora
			and c.status = AGENDADA
			""")
	Page<ConsultaListagemDTO> consultasAgendadasDoPaciente(Long pacienteId, LocalDateTime agora, Pageable pageable);
	
	@Query("""
			select c from Consulta c
			where c.status = FINALIZADA
			""")
	Page<ConsultaListagemDTO> consultasFinalizadas(Pageable pageable);
	
	@Query("""
			select c from Consulta c
			where c.medico.id = :medicoId
			and c.status = FINALIZADA
			""")
	Page<ConsultaListagemDTO> consultasFinalizadasDoMedico(Long medicoId, Pageable pageable);
	
	@Query("""
			select c from Consulta c
			where c.paciente.id = :pacienteId
			and c.status = FINALIZADA
			""")
	Page<ConsultaListagemDTO> consultasFinalizadasDoPaciente(Long pacienteId, Pageable pageable);
	
	@Query(value = """
			select * from consulta
			where paciente_id = :pacienteId
			and DATE(data) = DATE(:data)
			and status != 'DISPONIVEL'
			""", nativeQuery = true)
	Consulta consultaDoPacienteNoDia(Long pacienteId, LocalDateTime data);
}
