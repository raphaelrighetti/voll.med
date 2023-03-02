package med.voll.api.controller.medico;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.dto.MedicoAtualizacaoDTO;
import med.voll.api.domain.medico.dto.MedicoCadastroDTO;
import med.voll.api.domain.medico.dto.MedicoDetalhamentoDTO;
import med.voll.api.domain.medico.dto.MedicoListagemDTO;
import med.voll.api.domain.medico.entity.Medico;
import med.voll.api.domain.medico.repository.MedicoRepository;
import med.voll.api.domain.medico.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoDetalhamentoDTO> cadastrar(
            @RequestBody @Valid MedicoCadastroDTO dados, UriComponentsBuilder uriBuilder
    ) {
        MedicoDetalhamentoDTO dto = service.cadastrar(dados);
        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDetalhamentoDTO> detalhar(@PathVariable Long id) {
        MedicoDetalhamentoDTO dto = service.detalhar(id);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<MedicoListagemDTO>> listar(@PageableDefault(sort = {"nome"}) Pageable pageable) {
        Page<MedicoListagemDTO> page = service.listar(pageable);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MedicoDetalhamentoDTO> atualizar(
            @PathVariable Long id, @RequestBody @Valid MedicoAtualizacaoDTO dados
    ) {
        MedicoDetalhamentoDTO dto = service.atualizar(id, dados);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inativar(@PathVariable Long id) {
        service.inativar(id);

        return ResponseEntity.noContent().build();
    }
}
