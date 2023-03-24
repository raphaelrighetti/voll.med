package med.voll.api.domain.consulta.cancelamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.domain.medico.Especialidade;

public interface CancelamentoRepository extends JpaRepository<Cancelamento, Long> {
	
	@Query("""
			select c from Cancelamento c
			where c.paciente.id = :pacienteId
			""")
	List<Cancelamento> cancelamentosDoPaciente(Long pacienteId);
	
	@Query("""
			select c from Cancelamento c
			where c.consulta.medico.id = :medicoId
			""")
	List<Cancelamento> cancelamentosDoMedico(Long medicoId);
	
	@Query("""
			select c from Cancelamento c
			where c.consulta.medico.especialidade = :especialidade
			""")
	List<Cancelamento> cancelamentosPorEspecialidade(Especialidade especialidade);
}
