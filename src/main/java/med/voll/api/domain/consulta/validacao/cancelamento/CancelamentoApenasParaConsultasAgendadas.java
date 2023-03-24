package med.voll.api.domain.consulta.validacao.cancelamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.ConsultaStatus;
import med.voll.api.domain.consulta.cancelamento.dto.CancelamentoDTO;
import med.voll.api.domain.consulta.validacao.ValidacaoCancelamentoConsulta;
import med.voll.api.domain.genericos.exception.ValidacaoConsultaException;

@Component
public class CancelamentoApenasParaConsultasAgendadas implements ValidacaoCancelamentoConsulta {

	@Autowired
	private ConsultaRepository repository;
	
	@Override
	public void validar(CancelamentoDTO dados) {
		Consulta consulta = repository.getReferenceById(dados.consultaId());
		
		if (consulta.getStatus() != ConsultaStatus.AGENDADA) {
			throw new ValidacaoConsultaException("Uma consulta só pode ser cancelada se ela estiver agendada");
		}
	}

}
