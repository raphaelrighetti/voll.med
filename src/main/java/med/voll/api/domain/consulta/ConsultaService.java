package med.voll.api.domain.consulta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.consulta.cancelamento.Cancelamento;
import med.voll.api.domain.consulta.cancelamento.CancelamentoRepository;
import med.voll.api.domain.consulta.cancelamento.dto.CancelamentoDTO;
import med.voll.api.domain.consulta.cancelamento.dto.CancelamentoDetalhamentoDTO;
import med.voll.api.domain.consulta.cancelamento.dto.CancelamentoListagemDTO;
import med.voll.api.domain.consulta.dia.Dia;
import med.voll.api.domain.consulta.dia.DiaRepository;
import med.voll.api.domain.consulta.dto.ConsultaAgendamentoDTO;
import med.voll.api.domain.consulta.dto.ConsultaDetalhamentoDTO;
import med.voll.api.domain.consulta.dto.ConsultaDisponivelListagemDTO;
import med.voll.api.domain.consulta.dto.ConsultaListagemDTO;
import med.voll.api.domain.consulta.dto.ConsultasDisponiveisNoDiaDTO;
import med.voll.api.domain.consulta.validacao.ValidacaoAgendamentoConsulta;
import med.voll.api.domain.consulta.validacao.ValidacaoCancelamentoConsulta;
import med.voll.api.domain.genericos.exception.ValidacaoConsultaException;
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
	private DiaRepository diaRepository;
	
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
	
	public Page<ConsultaDisponivelListagemDTO> listarConsultasDisponiveis(Pageable pageable) {
		Page<ConsultaDisponivelListagemDTO> page = consultaRepository.consultasDisponiveis(agora(), pageable);
		
		return page;
	}
	
	public Page<ConsultasDisponiveisNoDiaDTO> listarConsultasDisponiveis(Long medicoId, Especialidade especialidade, Pageable pageable) {
		if (medicoId != null && !medicoRepository.existsById(medicoId)) {
			throw new EntityNotFoundException();
		}
		
		if (especialidade != null && medicoRepository.medicosPorEspecialidade(especialidade).isEmpty()) {
			throw new EntityNotFoundException();
		}
		
		List<Dia> dias = diaRepository.diasNoPresenteOuFuturo(LocalDate.now());
		List<ConsultasDisponiveisNoDiaDTO> dtos = new ArrayList<>();
		
		dias.forEach(dia -> {
			List<ConsultaDisponivelListagemDTO> consultas;
			
			if (medicoId != null && especialidade != null) {
				throw new ValidacaoConsultaException("Apenas um parâmetro deve ser passado");
			} else if (medicoId != null) {
				consultas = consultaRepository.consultasDisponiveisNoDiaDoMedico(medicoId, dia.getId(), agora());
			} else if (especialidade != null) {
				consultas = consultaRepository.consultasDisponiveisNoDiaPorEspecialidade(especialidade, dia.getId(), agora());
			} else {
				throw new ValidacaoConsultaException("Um parâmetro precisa ser passado");
			}
			
			ConsultasDisponiveisNoDiaDTO dto = new ConsultasDisponiveisNoDiaDTO(dia.getId(), consultas);
			
			dtos.add(dto);
		});
		
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), dtos.size());
		
		Page<ConsultasDisponiveisNoDiaDTO> page = new PageImpl<>(dtos.subList(start, end), pageable, dtos.size());
		
		return page;
	}
	
	public Page<ConsultaListagemDTO> listarConsultasAgendadas(Pageable pageable) {
		Page<ConsultaListagemDTO> page = consultaRepository.consultasAgendadas(agora(), pageable);
		
		return page;
	}
	
	public Page<ConsultaListagemDTO> listarConsultasAgendadas(Long medicoId, Long pacienteId, Pageable pageable) {
		if (medicoId != null && !medicoRepository.existsById(medicoId)) {
			throw new EntityNotFoundException();
		}
		
		if (pacienteId != null && !pacienteRepository.existsById(pacienteId)) {
			throw new EntityNotFoundException();
		}
		
		Page<ConsultaListagemDTO> page;
		
		if (medicoId != null && pacienteId != null) {
			throw new ValidacaoConsultaException("Apenas um parâmetro deve ser passado");
		} else if (medicoId != null) {
			page = consultaRepository.consultasAgendadasDoMedico(medicoId, agora(), pageable);
		} else if (pacienteId != null) {
			page = consultaRepository.consultasAgendadasDoPaciente(pacienteId, agora(), pageable);
		} else {
			throw new ValidacaoConsultaException("Um parâmetro precisa ser passado");
		}
		
		return page;
	}
	
	public Page<ConsultaListagemDTO> listarConsultasFinalizadas(Pageable pageable) {
		Page<ConsultaListagemDTO> page = consultaRepository.consultasFinalizadas(pageable);
		
		return page;
	}
	
	public Page<ConsultaListagemDTO> listarConsultasFinalizadas(Long medicoId, Long pacienteId, Pageable pageable) {
		if (medicoId != null && !medicoRepository.existsById(medicoId)) {
			throw new EntityNotFoundException();
		}
		
		if (pacienteId != null && !pacienteRepository.existsById(pacienteId)) {
			throw new EntityNotFoundException();
		}
		
		Page<ConsultaListagemDTO> page;
		
		if (medicoId != null && pacienteId != null) {
			throw new ValidacaoConsultaException("Apenas um parâmetro deve ser passado");
		} else if (medicoId != null) {
			page = consultaRepository.consultasFinalizadasDoMedico(medicoId, pageable);
		} else if (pacienteId != null) {
			page = consultaRepository.consultasFinalizadasDoPaciente(pacienteId, pageable);
		} else {
			throw new ValidacaoConsultaException("Um parâmetro precisa ser passado");
		}
		
		return page;
	}
	
	public CancelamentoDetalhamentoDTO detalharCancelamento(Long id) {
		Cancelamento cancelamento = cancelamentoRepository.getReferenceById(id);
		
		return new CancelamentoDetalhamentoDTO(cancelamento);
	}
	
	public Page<CancelamentoListagemDTO> listarCancelamentos(Pageable pageable) {
		Page<CancelamentoListagemDTO> page = cancelamentoRepository.findAll(pageable).map(CancelamentoListagemDTO::new);
		
		return page;
	}
	
	public Page<CancelamentoListagemDTO> listarCancelamentos(Long medicoId, Long pacienteId, Pageable pageable) {
		if (medicoId != null && !medicoRepository.existsById(medicoId)) {
			throw new EntityNotFoundException();
		}
		
		if (pacienteId != null && !pacienteRepository.existsById(pacienteId)) {
			throw new EntityNotFoundException();
		}
		
		Page<CancelamentoListagemDTO> page;
		
		if (medicoId != null && pacienteId != null) {
			throw new ValidacaoConsultaException("Apenas um parâmetro deve ser passado");
		} else if (medicoId != null) {
			page = cancelamentoRepository.cancelamentosDoMedico(medicoId, pageable);
		} else if (pacienteId != null) {
			page = cancelamentoRepository.cancelamentosDoPaciente(pacienteId, pageable);
		} else {
			throw new ValidacaoConsultaException("Um parâmetro precisa ser passado");
		}
		
		return page;
	}
	
	private LocalDateTime agora() {
		return LocalDateTime.now();
	}
}
