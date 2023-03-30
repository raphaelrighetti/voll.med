package med.voll.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import med.voll.api.domain.consulta.dto.ConsultasDisponiveisNoDiaDTO;
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
	public ResponseEntity<Page<ConsultaDisponivelListagemDTO>> listarConsultasDisponiveis(Pageable pageable) {
		Page<ConsultaDisponivelListagemDTO> page = service.listarConsultasDisponiveis(pageable);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/disponivel/dia")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<Page<ConsultasDisponiveisNoDiaDTO>> listarConsultasDisponiveis(
			@RequestParam(required = false, name = "medico") Long medicoId, @RequestParam(required = false) Especialidade especialidade, Pageable pageable) {
		Page<ConsultasDisponiveisNoDiaDTO> page = service.listarConsultasDisponiveis(medicoId, especialidade, pageable);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/agendada")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<Page<ConsultaListagemDTO>> listarConsultasAgendadas(Pageable pageable) {
		Page<ConsultaListagemDTO> page = service.listarConsultasAgendadas(pageable);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/agendada/params")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<Page<ConsultaListagemDTO>> listarConsultasAgendadas(
			@RequestParam(required = false, name = "medico") Long medicoId, @RequestParam(required = false, name = "paciente") Long pacienteId, Pageable pageable) {
		Page<ConsultaListagemDTO> page = service.listarConsultasAgendadas(medicoId, pacienteId, pageable);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/finalizada")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<Page<ConsultaListagemDTO>> listarConsultasFinalizadas(Pageable pageable) {
		Page<ConsultaListagemDTO> page = service.listarConsultasFinalizadas(pageable);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/finalizada/params")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<Page<ConsultaListagemDTO>> listarConsultasFinzalizadas(
			@RequestParam(required = false, name = "medico") Long medicoId, @RequestParam(required = false, name = "paciente") Long pacienteId, Pageable pageable) {
		Page<ConsultaListagemDTO> page = service.listarConsultasFinalizadas(medicoId, pacienteId, pageable);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/cancelada/{id}")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<CancelamentoDetalhamentoDTO> detalharCancelamento(@PathVariable Long id) {
		CancelamentoDetalhamentoDTO dto = service.detalharCancelamento(id);
		
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/cancelada")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<Page<CancelamentoListagemDTO>> listarCancelamentos(Pageable pageable) {
		Page<CancelamentoListagemDTO> page = service.listarCancelamentos(pageable);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/cancelada/params")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<Page<CancelamentoListagemDTO>> listarCancelamentos(
			@RequestParam(required = false, name = "medico") Long medicoId, @RequestParam(required = false, name = "paciente") Long pacienteId, Pageable pageable) {
		Page<CancelamentoListagemDTO> page = service.listarCancelamentos(medicoId, pacienteId, pageable);
		
		return ResponseEntity.ok(page);
	}
}
