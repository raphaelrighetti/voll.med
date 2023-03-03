package med.voll.api.controller.paciente;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.dto.PacienteAtualizacaoDTO;
import med.voll.api.domain.paciente.dto.PacienteCadastroDTO;
import med.voll.api.domain.paciente.dto.PacienteDetalhamentoDTO;
import med.voll.api.domain.paciente.dto.PacienteListagemDTO;
import med.voll.api.domain.paciente.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<PacienteDetalhamentoDTO> cadastrar(
            @RequestBody @Valid PacienteCadastroDTO dados, UriComponentsBuilder uriBuilder
    ) {
        PacienteDetalhamentoDTO dto = service.cadastrar(dados);
        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<PacienteDetalhamentoDTO> detalhar(@PathVariable Long id) {
        PacienteDetalhamentoDTO dto = service.detalhar(id);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<PacienteListagemDTO>> listar(@PageableDefault(sort = {"nome"}) Pageable pageable) {
        Page<PacienteListagemDTO> page = service.listar(pageable);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<PacienteDetalhamentoDTO> atualizar(
            @PathVariable Long id, @RequestBody @Valid PacienteAtualizacaoDTO dados
    ) {
        PacienteDetalhamentoDTO dto = service.atualizar(id, dados);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity inativar(@PathVariable Long id) {
        service.inativar(id);

        return ResponseEntity.noContent().build();
    }
}
