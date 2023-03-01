package med.voll.api.controller.consulta;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.consulta.ConsultaDTOAgendamento;
import med.voll.api.dto.consulta.ConsultaDTOCancelamento;
import med.voll.api.dto.consulta.ConsultaDTODetalhamento;
import med.voll.api.service.consulta.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<ConsultaDTODetalhamento> agendar(
            @RequestBody @Valid ConsultaDTOAgendamento dados,
            UriComponentsBuilder uriBuilder
    ) {
        ConsultaDTODetalhamento dto = service.agendar(dados);

        URI uri = uriBuilder.path("/consultas/{id}").buildAndExpand(dados.medicoId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid ConsultaDTOCancelamento dados) {
        service.cancelar(dados);

        return ResponseEntity.noContent().build();
    }
}
