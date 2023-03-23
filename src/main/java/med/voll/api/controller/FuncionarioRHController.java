package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.rh.FuncionarioRHService;
import med.voll.api.domain.rh.dto.FuncionarioRHAtualizacaoDTO;
import med.voll.api.domain.rh.dto.FuncionarioRHCadastroDTO;
import med.voll.api.domain.rh.dto.FuncionarioRHDetalhamentoDTO;
import med.voll.api.domain.rh.dto.FuncionarioRHListagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/rh")
public class FuncionarioRHController {

    @Autowired
    private FuncionarioRHService service;

    @PostMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<FuncionarioRHDetalhamentoDTO> cadastrar(
            @RequestBody @Valid FuncionarioRHCadastroDTO dados, UriComponentsBuilder uriBuilder
    ) {
        FuncionarioRHDetalhamentoDTO dto = service.cadastrar(dados);
        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<FuncionarioRHDetalhamentoDTO> detalhar(@PathVariable Long id) {
        FuncionarioRHDetalhamentoDTO dto = service.detalhar(id);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<FuncionarioRHListagemDTO>> listar(@PageableDefault(sort = {"nome"}) Pageable pageable) {
        Page<FuncionarioRHListagemDTO> page = service.listar(pageable);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<FuncionarioRHDetalhamentoDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid FuncionarioRHAtualizacaoDTO dados
    ) {
        FuncionarioRHDetalhamentoDTO dto = service.atualizar(id, dados);

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
