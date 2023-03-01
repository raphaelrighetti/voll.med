package med.voll.api.controller.medico;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.medico.MedicoDTOAtualizacao;
import med.voll.api.dto.medico.MedicoDTOCadastro;
import med.voll.api.dto.medico.MedicoDTODetalhe;
import med.voll.api.dto.medico.MedicoDTOListagem;
import med.voll.api.entity.medico.Medico;
import med.voll.api.repository.medico.MedicoRepository;
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
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoDTODetalhe> registrar(
            @RequestBody @Valid MedicoDTOCadastro dados,
            UriComponentsBuilder uriBuilder
    ) {
        Medico medico = new Medico(dados);

        repository.save(medico);
        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicoDTODetalhe(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoDTOListagem>> listar(@PageableDefault(sort = {"nome"}) Pageable pageable) {
        Page<MedicoDTOListagem> page = repository.findByStatusTrue(pageable);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTODetalhe> detalhar(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new MedicoDTODetalhe(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MedicoDTODetalhe> atualizar(@RequestBody @Valid MedicoDTOAtualizacao dados) {
        Medico medico = repository.getReferenceById(dados.id());
        medico.atualizarCampos(dados);

        return ResponseEntity.ok(new MedicoDTODetalhe(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.getReferenceById(id).inativar();

        return ResponseEntity.noContent().build();
    }
}
