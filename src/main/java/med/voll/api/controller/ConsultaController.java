package med.voll.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.ConsultaService;
import med.voll.api.domain.consulta.cancelamento.dto.CancelamentoDTO;
import med.voll.api.domain.consulta.cancelamento.dto.CancelamentoDetalhamentoDTO;
import med.voll.api.domain.consulta.cancelamento.dto.CancelamentoListagemDTO;
import med.voll.api.domain.consulta.dto.ConsultaListagemDTO;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.consulta.dto.ConsultaAgendamentoDTO;
import med.voll.api.domain.consulta.dto.ConsultaDetalhamentoDTO;
import med.voll.api.domain.consulta.dto.ConsultaDisponivelListagemDTO;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
	
	@Autowired
	private ConsultaService service;
	
	@PostMapping("/agendar")
	@Transactional
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<ConsultaDetalhamentoDTO> agendar(@RequestBody @Valid ConsultaAgendamentoDTO dados, UriComponentsBuilder uriBuilder) {
		ConsultaDetalhamentoDTO dto = service.agendar(dados);
		
		URI uri = uriBuilder.path("/consultas/{id}").buildAndExpand(dto.id()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
	
	@DeleteMapping("/cancelar")
	@Transactional
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<CancelamentoDetalhamentoDTO> cancelar(@RequestBody @Valid CancelamentoDTO dados) {
		CancelamentoDetalhamentoDTO dto = service.cancelar(dados);
		
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/{id}")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<ConsultaDetalhamentoDTO> detalhar(@PathVariable Long id) {
		ConsultaDetalhamentoDTO dto = service.detalhar(id);
		
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/disponivel")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<List<ConsultaDisponivelListagemDTO>> listarConsultasDisponiveis() {
		List<ConsultaDisponivelListagemDTO> dtos = service.listarConsultasDisponiveis();
		
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/disponivel/medico/{medicoId}")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<List<ConsultaDisponivelListagemDTO>> listarConsultasDisponiveisDoMedico(@PathVariable Long medicoId) {
		List<ConsultaDisponivelListagemDTO> dtos = service.listarConsultasDisponiveisDoMedico(medicoId);
		
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/agendada")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<List<ConsultaListagemDTO>> listarConsultasAgendadas() {
		List<ConsultaListagemDTO> dtos = service.listarConsultasAgendadas();
		
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/agendada/medico/{medicoId}")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<List<ConsultaListagemDTO>> listarConsultasAgendadasDoMedico(@PathVariable Long medicoId) {
		List<ConsultaListagemDTO> dtos = service.listarConsultasAgendadasDoMedico(medicoId);
		
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/agendada/paciente/{pacienteId}")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<List<ConsultaListagemDTO>> listarConsultasAgendadasDoPaciente(@PathVariable Long pacienteId) {
		List<ConsultaListagemDTO> dtos = service.listarConsultasAgendadasDoPaciente(pacienteId);
		
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/finalizada")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<List<ConsultaListagemDTO>> listarConsultasFinalizadas() {
		List<ConsultaListagemDTO> dtos = service.listarConsultasFinalizadas();
		
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/finalizada/medico/{medicoId}")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<List<ConsultaListagemDTO>> listarConsultasFinalizadasDoMedico(@PathVariable Long medicoId) {
		List<ConsultaListagemDTO> dtos = service.listarConsultasFinalizadasDoMedico(medicoId);
		
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/finalizada/paciente/{pacienteId}")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<List<ConsultaListagemDTO>> listarConsultasFinalizadasDoPaciente(@PathVariable Long pacienteId) {
		List<ConsultaListagemDTO> dtos = service.listarConsultasFinalizadasDoPaciente(pacienteId);
		
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/cancelada/{id}")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<CancelamentoDetalhamentoDTO> detalharCancelamento(@PathVariable Long id) {
		CancelamentoDetalhamentoDTO dto = service.detalharCancelamento(id);
		
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/cancelada")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<List<CancelamentoListagemDTO>> listarCancelamentos() {
		List<CancelamentoListagemDTO> dtos = service.listarCancelamentos();
		
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/cancelada/medico/{medicoId}")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<List<CancelamentoListagemDTO>> listarCancelamentosDoMedico(@PathVariable Long medicoId) {
		List<CancelamentoListagemDTO> dtos = service.listarCancelamentosDoMedico(medicoId);
		
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/cancelada/paciente/{pacienteId}")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<List<CancelamentoListagemDTO>> listarCancelamentosDoPaciente(@PathVariable Long pacienteId) {
		List<CancelamentoListagemDTO> dtos = service.listarCancelamentosDoPaciente(pacienteId);
		
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/cancelada/especialidade/{especialidade}")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<List<CancelamentoListagemDTO>> listarCancelamentosPorEspecialidade(@PathVariable Especialidade especialidade) {
		List<CancelamentoListagemDTO> dtos = service.listarCancelamentosPorEspecialidade(especialidade);
		
		return ResponseEntity.ok(dtos);
	}
}
