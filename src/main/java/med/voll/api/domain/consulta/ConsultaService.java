package med.voll.api.domain.consulta;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.consulta.cancelamento.Cancelamento;
import med.voll.api.domain.consulta.cancelamento.CancelamentoRepository;
import med.voll.api.domain.consulta.cancelamento.dto.CancelamentoDTO;
import med.voll.api.domain.consulta.cancelamento.dto.CancelamentoDetalhamentoDTO;
import med.voll.api.domain.consulta.cancelamento.dto.CancelamentoListagemDTO;
import med.voll.api.domain.consulta.dto.ConsultaAgendamentoDTO;
import med.voll.api.domain.consulta.dto.ConsultaDetalhamentoDTO;
import med.voll.api.domain.consulta.dto.ConsultaDisponivelListagemDTO;
import med.voll.api.domain.consulta.dto.ConsultaListagemDTO;
import med.voll.api.domain.consulta.validacao.ValidacaoAgendamentoConsulta;
import med.voll.api.domain.consulta.validacao.ValidacaoCancelamentoConsulta;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;

@Service
public class ConsultaService {
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private CancelamentoRepository cancelamentoRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private List<ValidacaoAgendamentoConsulta> validacoesAgendamento;
	
	@Autowired
	private List<ValidacaoCancelamentoConsulta> validacoesCancelamento;
	
	public ConsultaDetalhamentoDTO agendar(ConsultaAgendamentoDTO dados) {
		if (!consultaRepository.existsById(dados.id())) {
			throw new EntityNotFoundException("Consulta não encontrada");
		}
		
		if (!pacienteRepository.existsById(dados.pacienteId())) {
			throw new EntityNotFoundException("Paciente não encontrado");
		}
		
		validacoesAgendamento.forEach(validacao -> validacao.validar(dados));
		
		Consulta consulta = consultaRepository.getReferenceById(dados.id());
		Paciente paciente = pacienteRepository.getReferenceById(dados.pacienteId());
		
		consulta.setPaciente(paciente);
		consulta.setStatus(ConsultaStatus.AGENDADA);
		
		return new ConsultaDetalhamentoDTO(consulta);
	}
	
	public CancelamentoDetalhamentoDTO cancelar(CancelamentoDTO dados) {
		if (!consultaRepository.existsById(dados.consultaId())) {
			throw new EntityNotFoundException("Consulta não encontrada");
		}
		
		validacoesCancelamento.forEach(validacao -> validacao.validar(dados));
		
		Consulta consulta = consultaRepository.getReferenceById(dados.consultaId());
		Cancelamento cancelamento = new Cancelamento();
		
		cancelamento.setConsulta(consulta);
		cancelamento.setPaciente(consulta.getPaciente());
		cancelamento.setMotivo(dados.motivo());
		
		cancelamentoRepository.save(cancelamento);
		
		consulta.setPaciente(null);
		consulta.setStatus(ConsultaStatus.DISPONIVEL);
		
		return new CancelamentoDetalhamentoDTO(cancelamento);
	}
	
	public ConsultaDetalhamentoDTO detalhar(Long id) {
		Consulta consulta = consultaRepository.getReferenceById(id);
		
		return new ConsultaDetalhamentoDTO(consulta);
	}
	
	public List<ConsultaDisponivelListagemDTO> listarConsultasDisponiveis() {
		List<Consulta> consultas = consultaRepository.consultasDisponiveis(agora());
		
		List<ConsultaDisponivelListagemDTO> dtos = consultas.stream().map(ConsultaDisponivelListagemDTO::new).toList();
		
		return dtos;
	}
	
	public List<ConsultaDisponivelListagemDTO> listarConsultasDisponiveisDoMedico(Long medicoId) {
		if (!medicoRepository.existsById(medicoId)) {
			throw new EntityNotFoundException("Médico não encontrado");
		}
		
		List<Consulta> consultas = consultaRepository.consultasDisponiveisDoMedico(medicoId, agora());
		
		List<ConsultaDisponivelListagemDTO> dtos = consultas.stream().map(ConsultaDisponivelListagemDTO::new).toList();
		
		return dtos;
	}
	
	public List<ConsultaListagemDTO> listarConsultasAgendadas() {
		List<Consulta> consultas = consultaRepository.consultasAgendadas(agora());
		
		List<ConsultaListagemDTO> dtos = consultas.stream().map(ConsultaListagemDTO::new).toList();
		
		return dtos;
	}
	
	public List<ConsultaListagemDTO> listarConsultasAgendadasDoMedico(Long medicoId) {
		if (!medicoRepository.existsById(medicoId)) {
			throw new EntityNotFoundException("Médico não encontrado");
		}
		
		List<Consulta> consultas = consultaRepository.consultasAgendadasDoMedico(medicoId, agora());
		
		List<ConsultaListagemDTO> dtos = consultas.stream().map(ConsultaListagemDTO::new).toList();
		
		return dtos;
	}
	
	public List<ConsultaListagemDTO> listarConsultasAgendadasDoPaciente(Long pacienteId) {
		if (!pacienteRepository.existsById(pacienteId)) {
			throw new EntityNotFoundException("Paciente não encontrado");
		}
		
		List<Consulta> consultas = consultaRepository.consultasAgendadasDoPaciente(pacienteId, agora());
		
		List<ConsultaListagemDTO> dtos = consultas.stream().map(ConsultaListagemDTO::new).toList();
		
		return dtos;
	}
	
	public List<ConsultaListagemDTO> listarConsultasFinalizadas() {
		List<Consulta> consultas = consultaRepository.consultasFinalizadas();
		
		List<ConsultaListagemDTO> dtos = consultas.stream().map(ConsultaListagemDTO::new).toList();
		
		return dtos;
	}
	
	public List<ConsultaListagemDTO> listarConsultasFinalizadasDoMedico(Long medicoId) {
		if (!medicoRepository.existsById(medicoId)) {
			throw new EntityNotFoundException("Médico não encontrado");
		}
		
		List<Consulta> consultas = consultaRepository.consultasFinalizadasDoMedico(medicoId);
		
		List<ConsultaListagemDTO> dtos = consultas.stream().map(ConsultaListagemDTO::new).toList();
		
		return dtos;
	}
	
	public List<ConsultaListagemDTO> listarConsultasFinalizadasDoPaciente(Long pacienteId) {
		if (!pacienteRepository.existsById(pacienteId)) {
			throw new EntityNotFoundException("Paciente não encontrado");
		}
		
		List<Consulta> consultas = consultaRepository.consultasFinalizadasDoPaciente(pacienteId);
		
		List<ConsultaListagemDTO> dtos = consultas.stream().map(ConsultaListagemDTO::new).toList();
		
		return dtos;
	}
	
	public CancelamentoDetalhamentoDTO detalharCancelamento(Long id) {
		Cancelamento cancelamento = cancelamentoRepository.getReferenceById(id);
		
		return new CancelamentoDetalhamentoDTO(cancelamento);
	}
	
	public List<CancelamentoListagemDTO> listarCancelamentos() {
		List<Cancelamento> cancelamentos = cancelamentoRepository.findAll();
		
		List<CancelamentoListagemDTO> dtos = cancelamentos.stream().map(CancelamentoListagemDTO::new).toList();
		
		return dtos;
	}
	
	public List<CancelamentoListagemDTO> listarCancelamentosDoMedico(Long medicoId) {
		if (!medicoRepository.existsById(medicoId)) {
			throw new EntityNotFoundException("Médico não encontrado");
		}
		
		List<Cancelamento> cancelamentos = cancelamentoRepository.cancelamentosDoMedico(medicoId);
		
		List<CancelamentoListagemDTO> dtos = cancelamentos.stream().map(CancelamentoListagemDTO::new).toList();
		
		return dtos;
	}
	
	public List<CancelamentoListagemDTO> listarCancelamentosDoPaciente(Long pacienteId) {
		if (!pacienteRepository.existsById(pacienteId)) {
			throw new EntityNotFoundException("Paciente não encontrado");
		}
		
		List<Cancelamento> cancelamentos = cancelamentoRepository.cancelamentosDoPaciente(pacienteId);
		
		List<CancelamentoListagemDTO> dtos = cancelamentos.stream().map(CancelamentoListagemDTO::new).toList();
		
		return dtos;
	}
	
	public List<CancelamentoListagemDTO> listarCancelamentosPorEspecialidade(Especialidade especialidade) {
		List<Cancelamento> cancelamentos = cancelamentoRepository.cancelamentosPorEspecialidade(especialidade);
		
		List<CancelamentoListagemDTO> dtos = cancelamentos.stream().map(CancelamentoListagemDTO::new).toList();
		
		return dtos;
	}
	
	private LocalDateTime agora() {
		return LocalDateTime.now();
	}
}
