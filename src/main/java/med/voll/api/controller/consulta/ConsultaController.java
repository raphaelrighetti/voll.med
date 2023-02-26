package med.voll.api.controller.consulta;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.consulta.ConsultaDTOAgendamento;
import med.voll.api.dto.consulta.ConsultaDTODetalhamento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @PostMapping
    @Transactional
    public ResponseEntity<ConsultaDTODetalhamento> agendar(
            @RequestBody @Valid ConsultaDTOAgendamento dados,
            UriComponentsBuilder uriBuilder
    ) {
        System.out.println(dados);

        URI uri = uriBuilder.path("/consultas/{id}").buildAndExpand(dados.medicoId()).toUri();

        return ResponseEntity.created(uri).body(new ConsultaDTODetalhamento(null, null, null, null));
    }
}
