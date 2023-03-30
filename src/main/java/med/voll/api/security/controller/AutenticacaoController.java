package med.voll.api.security.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.dto.UsuarioAutenticacaoDTO;
import med.voll.api.security.service.JWTService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
        String role = new ArrayList<>(usuario.getAuthorities()).get(0).toString();

        return new JWTDTO(jwtService.gerarToken(usuario), role);
    }

    private record JWTDTO(String token, String role) { }
}
