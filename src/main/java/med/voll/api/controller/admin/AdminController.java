package med.voll.api.controller.admin;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.admin.dto.AdminAtualizacaoDTO;
import med.voll.api.domain.admin.dto.AdminCadastroDTO;
import med.voll.api.domain.admin.dto.AdminDetalhamentoDTO;
import med.voll.api.domain.admin.dto.AdminListagemDTO;
import med.voll.api.domain.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<AdminDetalhamentoDTO> cadastrar(
            @RequestBody @Valid AdminCadastroDTO dados, UriComponentsBuilder uriBuilder
    ) {
        AdminDetalhamentoDTO dto = service.cadastrar(dados);
        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<AdminDetalhamentoDTO> detalhar(@PathVariable Long id) {
        AdminDetalhamentoDTO dto = service.detalhar(id);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<AdminListagemDTO>> listar(@PageableDefault(sort = {"nome"}) Pageable pageable) {
        Page<AdminListagemDTO> page = service.listar(pageable);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<AdminDetalhamentoDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AdminAtualizacaoDTO dados
    ) {
        AdminDetalhamentoDTO dto = service.atualizar(id, dados);

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
