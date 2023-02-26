package med.voll.api.controller.usuario;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.usuario.UsuarioDTOCadastro;
import med.voll.api.dto.usuario.UsuarioDTODetalhamento;
import med.voll.api.entity.usuario.Usuario;
import med.voll.api.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/registrar")
public class UsuarioCadastroController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDTODetalhamento> registrar(
            @RequestBody @Valid UsuarioDTOCadastro dados,
            UriComponentsBuilder uriBuilder
    ) {
        Usuario usuario = new Usuario(dados);

        repository.save(usuario);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new UsuarioDTODetalhamento(usuario));
    }
}
