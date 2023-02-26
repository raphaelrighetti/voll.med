package med.voll.api.controller.usuario;

import jakarta.validation.Valid;
import med.voll.api.dto.usuario.UsuarioDTOAutenticacao;
import med.voll.api.entity.usuario.Usuario;
import med.voll.api.service.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UsuarioAutenticacaoController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autentica(@RequestBody @Valid UsuarioDTOAutenticacao dados) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        Authentication authentication = manager.authenticate(authenticationToken);
        String jwtToken = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new JWTTokenDTO(jwtToken));
    }

    private record JWTTokenDTO(String token) {
    }
}
