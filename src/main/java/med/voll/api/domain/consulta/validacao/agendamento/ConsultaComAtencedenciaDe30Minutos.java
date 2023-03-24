package med.voll.api.domain.consulta.validacao.agendamento;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.dto.ConsultaAgendamentoDTO;
import med.voll.api.domain.consulta.validacao.ValidacaoAgendamentoConsulta;
import med.voll.api.domain.genericos.exception.ValidacaoConsultaException;

@Component
public class ConsultaComAtencedenciaDe30Minutos implements ValidacaoAgendamentoConsulta {

	@Autowired
	private ConsultaRepository repository;
	
	@Override
	public void validar(ConsultaAgendamentoDTO dados) {
		LocalDateTime dataAtual = LocalDateTime.now();
		LocalDateTime dataDaConsulta = repository.getReferenceById(dados.id()).getData();
		
		if (dataAtual.plusMinutes(30).isAfter(dataDaConsulta)) {
			throw new ValidacaoConsultaException("A consulta deve ser agendada com antecedência mínima de 30 minutos");
		}
	}
}
