package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.dto.MedicoAtualizacaoDTO;
import med.voll.api.domain.medico.dto.MedicoCadastroDTO;
import med.voll.api.domain.medico.dto.MedicoDetalhamentoDTO;
import med.voll.api.domain.medico.dto.MedicoListagemDTO;
import med.voll.api.domain.medico.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<MedicoDetalhamentoDTO> cadastrar(
            @RequestBody @Valid MedicoCadastroDTO dados, UriComponentsBuilder uriBuilder
    ) {
        MedicoDetalhamentoDTO dto = service.cadastrar(dados);
        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<MedicoDetalhamentoDTO> detalhar(@PathVariable Long id) {
        MedicoDetalhamentoDTO dto = service.detalhar(id);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<MedicoListagemDTO>> listar(@PageableDefault(sort = {"nome"}) Pageable pageable) {
        Page<MedicoListagemDTO> page = service.listar(pageable);

        return ResponseEntity.ok(page);
    }
    
    @GetMapping("/{id}/horarios")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<List<LocalDateTime>> listarHorariosDisponiveis(@PathVariable Long id) {
    	List<LocalDateTime> horariosDisponiveis = service.listarHorariosDisponiveis(id);
    	
    	return ResponseEntity.ok(horariosDisponiveis);
    }

    @PutMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<MedicoDetalhamentoDTO> atualizar(
            @PathVariable Long id, @RequestBody @Valid MedicoAtualizacaoDTO dados
    ) {
        MedicoDetalhamentoDTO dto = service.atualizar(id, dados);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        service.inativar(id);

        return ResponseEntity.noContent().build();
    }
}
