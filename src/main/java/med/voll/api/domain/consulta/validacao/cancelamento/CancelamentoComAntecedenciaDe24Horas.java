package med.voll.api.domain.consulta.validacao.cancelamento;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.cancelamento.dto.CancelamentoDTO;
import med.voll.api.domain.consulta.validacao.ValidacaoCancelamentoConsulta;
import med.voll.api.domain.genericos.exception.ValidacaoConsultaException;

@Component
public class CancelamentoComAntecedenciaDe24Horas implements ValidacaoCancelamentoConsulta {

	@Autowired
	private ConsultaRepository repository;
	
	@Override
	public void validar(CancelamentoDTO dados) {
		LocalDateTime dataAtual = LocalDateTime.now();
		LocalDateTime dataDaConsulta = repository.getReferenceById(dados.consultaId()).getData();
		
		if (dataAtual.plusHours(24).isAfter(dataDaConsulta)) {
			throw new ValidacaoConsultaException("Cancelamentos só podem ser efetuados com 24 horas de antecedência");
		}
	}

}
