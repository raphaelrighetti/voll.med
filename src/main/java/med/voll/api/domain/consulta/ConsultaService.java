package med.voll.api.domain.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.consulta.dto.ConsultaAgendamentoDTO;
import med.voll.api.domain.consulta.dto.ConsultaDetalhamentoDTO;
import med.voll.api.domain.consulta.dto.ConsultaDisponivelListagemDTO;
import med.voll.api.domain.consulta.validacao.ValidacaoAgendamentoConsulta;
import med.voll.api.domain.genericos.exception.ValidacaoConsultaException;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;

@Service
public class ConsultaService {
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private List<ValidacaoAgendamentoConsulta> validacoesAgendamento;
	
	public ConsultaDetalhamentoDTO agendar(ConsultaAgendamentoDTO dados) {
		Consulta consulta = consultaRepository.getReferenceById(dados.id());
		Paciente paciente = pacienteRepository.getReferenceById(dados.pacienteId());
		
		if (paciente == null) {
			throw new ValidacaoConsultaException("Paciente não encontrado");
		}
		if (consulta == null) {
			throw new ValidacaoConsultaException("Consulta não encontrada");
		}
		
		validacoesAgendamento.forEach(validacao -> {
			validacao.validar(dados);
		});
		
		consulta.setPaciente(paciente);
		consulta.setStatus(ConsultaStatus.AGENDADA);
		
		return new ConsultaDetalhamentoDTO(consulta);
	}
	
	public ConsultaDetalhamentoDTO detalhar(Long id) {
		Consulta consulta = consultaRepository.getReferenceById(id);
		
		return new ConsultaDetalhamentoDTO(consulta);
	}
	
	public List<ConsultaDisponivelListagemDTO> listarConsultasDisponiveis() {
		List<Consulta> consultas = consultaRepository.consultasDisponiveis();
		
		List<ConsultaDisponivelListagemDTO> dtos = consultas.stream().map(ConsultaDisponivelListagemDTO::new).toList();
		
		return dtos;
	}
	
	public List<ConsultaDisponivelListagemDTO> listarConsultasDisponiveisDoMedico(Long medicoId) {
		List<Consulta> consultas = consultaRepository.consultasDisponiveisDoMedico(medicoId);
		
		if (consultas.isEmpty()) {
			throw new EntityNotFoundException();
		}
		
		List<ConsultaDisponivelListagemDTO> dtos = consultas.stream().map(ConsultaDisponivelListagemDTO::new).toList();
		
		return dtos;
	}
}
