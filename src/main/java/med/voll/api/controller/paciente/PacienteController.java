package med.voll.api.controller.paciente;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.paciente.PacienteDTOAtualizacao;
import med.voll.api.dto.paciente.PacienteDTOCadastro;
import med.voll.api.dto.paciente.PacienteDTODetalhe;
import med.voll.api.dto.paciente.PacienteDTOListagem;
import med.voll.api.entity.paciente.Paciente;
import med.voll.api.repository.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<PacienteDTODetalhe> registraPaciente(
            @RequestBody @Valid PacienteDTOCadastro dados,
            UriComponentsBuilder uriBuilder
    ) {
        Paciente paciente = new Paciente(dados);

        repository.save(paciente);
        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new PacienteDTODetalhe(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteDTOListagem>> listar(@PageableDefault(sort = {"nome"}) Pageable pageable) {
        Page<PacienteDTOListagem> page = repository.findByStatusTrue(pageable);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTODetalhe> detalhar(@PathVariable Long id) {
        Paciente paciente = repository.getReferenceById(id);

        return ResponseEntity.ok(new PacienteDTODetalhe(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PacienteDTODetalhe> atualizar(@RequestBody PacienteDTOAtualizacao dados) {
        Optional<Paciente> paciente = repository.findById(dados.id());
        if (paciente.isEmpty()) return ResponseEntity.notFound().build();

        paciente.get().atualizarCampos(dados);

        return ResponseEntity.ok(new PacienteDTODetalhe(paciente.get()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        Optional<Paciente> paciente = repository.findById(id);
        if (paciente.isEmpty()) return ResponseEntity.notFound().build();

        paciente.get().inativar();

        return ResponseEntity.noContent().build();
    }
}
