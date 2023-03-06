package med.voll.api.domain.paciente;

import med.voll.api.domain.genericos.exception.AutorizacaoException;
import med.voll.api.domain.paciente.dto.PacienteAtualizacaoDTO;
import med.voll.api.domain.paciente.dto.PacienteCadastroDTO;
import med.voll.api.domain.paciente.dto.PacienteDetalhamentoDTO;
import med.voll.api.domain.paciente.dto.PacienteListagemDTO;
import med.voll.api.domain.security.autorizacao.Autoridades;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.domain.usuario.UsuarioRepository;
import med.voll.api.security.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PacienteDetalhamentoDTO cadastrar(PacienteCadastroDTO dados) {
        Paciente paciente = new Paciente(dados);

        Usuario usuario = new Usuario(dados);
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        usuarioRepository.save(usuario);

        paciente.setUsuario(usuario);
        pacienteRepository.save(paciente);

        return new PacienteDetalhamentoDTO(paciente);
    }

    public PacienteDetalhamentoDTO detalhar(Long id, String header) {
        Paciente paciente = pacienteRepository.getReferenceById(id);

        if (!ehAdmin(header)) {
            comparaEmail(paciente, header);
        }

        return new PacienteDetalhamentoDTO(paciente);
    }

    public Page<PacienteListagemDTO> listar(Pageable pageable) {
        return pacienteRepository.findPacientesAtivos(pageable);
    }

    public PacienteDetalhamentoDTO atualizar(Long id, PacienteAtualizacaoDTO dados, String header) {
        Paciente paciente = pacienteRepository.getReferenceById(id);

        if (!ehAdmin(header)) {
            comparaEmail(paciente, header);
        }

        paciente.atualizar(dados);

        return new PacienteDetalhamentoDTO(paciente);
    }

    public void inativar(Long id, String header) {
        Paciente paciente = pacienteRepository.getReferenceById(id);

        if (!ehAdmin(header)) {
            comparaEmail(paciente, header);
        }

        paciente.inativar();
    }

    private boolean ehAdmin(String header) {
        String token = header.replace("Bearer ", "");
        String subjectEmail = jwtService.getSubject(token);
        Usuario usuario = usuarioRepository.findByEmail(subjectEmail);

        return usuario.getAutoridade() == Autoridades.ROLE_ADMIN;
    }

    private void comparaEmail(Paciente paciente, String header) {
        String token = header.replace("Bearer ", "");
        String subjectEmail = jwtService.getSubject(token);

        if (!paciente.getUsuario().getEmail().equals(subjectEmail)) {
            throw new AutorizacaoException();
        }
    }
}
