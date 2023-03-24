package med.voll.api.domain.medico;

import med.voll.api.domain.medico.dto.MedicoAtualizacaoDTO;
import med.voll.api.domain.medico.dto.MedicoCadastroDTO;
import med.voll.api.domain.medico.dto.MedicoDetalhamentoDTO;
import med.voll.api.domain.medico.dto.MedicoListagemDTO;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.domain.usuario.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MedicoDetalhamentoDTO cadastrar(MedicoCadastroDTO dados) {
        Medico medico = new Medico(dados);

        Usuario usuario = new Usuario(dados);
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        usuarioRepository.save(usuario);

        medico.setUsuario(usuario);
        medicoRepository.save(medico);

        return new MedicoDetalhamentoDTO(medico);
    }

    public MedicoDetalhamentoDTO detalhar(Long id) {
        Medico medico = medicoRepository.getReferenceById(id);

        return new MedicoDetalhamentoDTO(medico);
    }

    public Page<MedicoListagemDTO> listar(Pageable pageable) {
        return medicoRepository.findMedicosAtivos(pageable);
    }

    public MedicoDetalhamentoDTO atualizar(Long id, MedicoAtualizacaoDTO dados) {
        Medico medico = medicoRepository.getReferenceById(id);

        medico.atualizar(dados);

        return new MedicoDetalhamentoDTO(medico);
    }

    public void inativar(Long id) {
        Medico medico = medicoRepository.getReferenceById(id);

        medico.inativar();
    }
}
