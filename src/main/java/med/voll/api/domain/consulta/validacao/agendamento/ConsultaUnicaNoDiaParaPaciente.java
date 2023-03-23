package med.voll.api.domain.consulta.validacao.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.dto.ConsultaAgendamentoDTO;
import med.voll.api.domain.consulta.validacao.ValidacaoAgendamentoConsulta;
import med.voll.api.domain.genericos.exception.ValidacaoConsultaException;

@Component
public class ConsultaUnicaNoDiaParaPaciente implements ValidacaoAgendamentoConsulta {

	@Autowired
	private ConsultaRepository repository;
	
	@Override
	public void validar(ConsultaAgendamentoDTO dados) {
		Consulta consulta = repository.getReferenceById(dados.id());
		Consulta consultaDoPacienteNoDia = repository.consultaDoPacienteNoDia(dados.pacienteId(), consulta.getData());
		
		if (consultaDoPacienteNoDia != null) {
			throw new ValidacaoConsultaException("Paciente já possui consulta no dia");
		}
	}
}
