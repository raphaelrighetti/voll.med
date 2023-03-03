package med.voll.api.domain.paciente.service;

import med.voll.api.domain.medico.dto.MedicoDetalhamentoDTO;
import med.voll.api.domain.medico.entity.Medico;
import med.voll.api.domain.paciente.dto.PacienteAtualizacaoDTO;
import med.voll.api.domain.paciente.dto.PacienteCadastroDTO;
import med.voll.api.domain.paciente.dto.PacienteDetalhamentoDTO;
import med.voll.api.domain.paciente.dto.PacienteListagemDTO;
import med.voll.api.domain.paciente.entity.Paciente;
import med.voll.api.domain.paciente.repository.PacienteRepository;
import med.voll.api.domain.usuario.entity.Usuario;
import med.voll.api.domain.usuario.repository.UsuarioRepository;
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

    public PacienteDetalhamentoDTO detalhar(Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);

        return new PacienteDetalhamentoDTO(paciente);
    }

    public Page<PacienteListagemDTO> listar(Pageable pageable) {
        return pacienteRepository.findPacientesAtivos(pageable);
    }

    public PacienteDetalhamentoDTO atualizar(Long id, PacienteAtualizacaoDTO dados) {
        Paciente paciente = pacienteRepository.getReferenceById(id);

        paciente.atualizar(dados);

        return new PacienteDetalhamentoDTO(paciente);
    }

    public void inativar(Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);

        paciente.inativar();
    }
}
