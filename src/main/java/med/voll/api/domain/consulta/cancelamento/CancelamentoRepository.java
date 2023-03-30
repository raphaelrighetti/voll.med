package med.voll.api.domain.consulta.cancelamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.domain.consulta.cancelamento.dto.CancelamentoListagemDTO;

public interface CancelamentoRepository extends JpaRepository<Cancelamento, Long> {
	
	@Query("""
			select c from Cancelamento c
			where c.paciente.id = :pacienteId
			""")
	Page<CancelamentoListagemDTO> cancelamentosDoPaciente(Long pacienteId, Pageable pageable);
	
	@Query("""
			select c from Cancelamento c
			where c.consulta.medico.id = :medicoId
			""")
	Page<CancelamentoListagemDTO> cancelamentosDoMedico(Long medicoId, Pageable pageable);
}
