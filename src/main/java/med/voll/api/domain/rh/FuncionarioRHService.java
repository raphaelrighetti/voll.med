package med.voll.api.domain.rh;

import med.voll.api.domain.admin.Admin;
import med.voll.api.domain.admin.dto.AdminAtualizacaoDTO;
import med.voll.api.domain.admin.dto.AdminCadastroDTO;
import med.voll.api.domain.admin.dto.AdminDetalhamentoDTO;
import med.voll.api.domain.admin.dto.AdminListagemDTO;
import med.voll.api.domain.rh.dto.FuncionarioRHAtualizacaoDTO;
import med.voll.api.domain.rh.dto.FuncionarioRHCadastroDTO;
import med.voll.api.domain.rh.dto.FuncionarioRHDetalhamentoDTO;
import med.voll.api.domain.rh.dto.FuncionarioRHListagemDTO;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.domain.usuario.UsuarioRepository;
import med.voll.api.security.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioRHService {

    @Autowired
    private FuncionarioRHRepository funcionarioRHRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public FuncionarioRHDetalhamentoDTO cadastrar(FuncionarioRHCadastroDTO dados) {
        FuncionarioRH funcionarioRH = new FuncionarioRH(dados);

        Usuario usuario = new Usuario(dados);
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        usuarioRepository.save(usuario);

        funcionarioRH.setUsuario(usuario);
        funcionarioRHRepository.save(funcionarioRH);

        return new FuncionarioRHDetalhamentoDTO(funcionarioRH);
    }

    public FuncionarioRHDetalhamentoDTO detalhar(Long id) {
        FuncionarioRH funcionarioRH = funcionarioRHRepository.getReferenceById(id);

        return new FuncionarioRHDetalhamentoDTO(funcionarioRH);
    }

    public Page<FuncionarioRHListagemDTO> listar(Pageable pageable) {
        return funcionarioRHRepository.findFuncionariosAtivos(pageable);
    }

    public FuncionarioRHDetalhamentoDTO atualizar(Long id, FuncionarioRHAtualizacaoDTO dados) {
        FuncionarioRH funcionarioRH = funcionarioRHRepository.getReferenceById(id);

        funcionarioRH.atualizar(dados);

        return new FuncionarioRHDetalhamentoDTO(funcionarioRH);
    }

    public void inativar(Long id) {
        FuncionarioRH funcionarioRH = funcionarioRHRepository.getReferenceById(id);

        funcionarioRH.inativar();
    }
}
