package med.voll.api.controller.paciente;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.dto.PacienteCadastroDTO;
import med.voll.api.domain.paciente.dto.PacienteDetalhamentoDTO;
import med.voll.api.domain.paciente.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    @Transactional
    public ResponseEntity<PacienteDetalhamentoDTO> cadastrar(
            @RequestBody @Valid PacienteCadastroDTO dados, UriComponentsBuilder uriBuilder
    ) {
        PacienteDetalhamentoDTO dto = service.cadastrar(dados);
        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}
