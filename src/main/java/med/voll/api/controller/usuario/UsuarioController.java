package med.voll.api.controller.usuario;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import med.voll.api.dto.usuario.UsuarioDTODetalhamento;
import med.voll.api.entity.usuario.Usuario;
import med.voll.api.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTODetalhamento> detalhar(@PathVariable Long id) {
        Usuario usuario = repository.getReferenceById(id);

        return ResponseEntity.ok(new UsuarioDTODetalhamento(usuario));
    }
}
