package med.voll.api.security.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.dto.UsuarioAutenticacaoDTO;
import med.voll.api.security.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JWTService jwtService;

    @PostMapping
    public JWTDTO autenticar(@RequestBody @Valid UsuarioAutenticacaoDTO dados) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());

        Authentication authentication = manager.authenticate(authenticationToken);
        UserDetails usuario = (UserDetails) authentication.getPrincipal();

        return new JWTDTO(jwtService.gerarToken(usuario));
    }

    private record JWTDTO(String token) { }
}
