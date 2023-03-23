package med.voll.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping("/disponivel/{medicoId}")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<List<ConsultaDisponivelListagemDTO>> listarConsultasDisponiveisDoMedico(@PathVariable Long medicoId) {
		List<ConsultaDisponivelListagemDTO> dtos = service.listarConsultasDisponiveisDoMedico(medicoId);
		
		return ResponseEntity.ok(dtos);
	}
}
