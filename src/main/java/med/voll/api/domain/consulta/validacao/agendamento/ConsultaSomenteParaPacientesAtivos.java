package med.voll.api.domain.consulta.validacao.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.dto.ConsultaAgendamentoDTO;
import med.voll.api.domain.consulta.validacao.ValidacaoAgendamentoConsulta;
import med.voll.api.domain.genericos.exception.ValidacaoConsultaException;
import med.voll.api.domain.paciente.PacienteRepository;

@Component
public class ConsultaSomenteParaPacientesAtivos implements ValidacaoAgendamentoConsulta {

	@Autowired
	private PacienteRepository repository;
	
	@Override
	public void validar(ConsultaAgendamentoDTO dados) {
		boolean pacienteAtivo = repository.getReferenceById(dados.pacienteId()).getUsuario().getAtivo();
		
		if (!pacienteAtivo) {
			throw new ValidacaoConsultaException("Pacientes inativos não podem agendar consultas");
		}
	}
}
