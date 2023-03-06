package med.voll.api.domain.admin;

import med.voll.api.domain.admin.Admin;
import med.voll.api.domain.admin.AdminRepository;
import med.voll.api.domain.admin.dto.AdminAtualizacaoDTO;
import med.voll.api.domain.admin.dto.AdminCadastroDTO;
import med.voll.api.domain.admin.dto.AdminDetalhamentoDTO;
import med.voll.api.domain.admin.dto.AdminListagemDTO;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.domain.usuario.UsuarioRepository;
import med.voll.api.security.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminDetalhamentoDTO cadastrar(AdminCadastroDTO dados) {
        Admin admin = new Admin(dados);

        Usuario usuario = new Usuario(dados);
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        usuarioRepository.save(usuario);

        admin.setUsuario(usuario);
        adminRepository.save(admin);

        return new AdminDetalhamentoDTO(admin);
    }

    public AdminDetalhamentoDTO detalhar(Long id) {
        Admin admin = adminRepository.getReferenceById(id);

        return new AdminDetalhamentoDTO(admin);
    }

    public Page<AdminListagemDTO> listar(Pageable pageable) {
        return adminRepository.findAdminsAtivos(pageable);
    }

    public AdminDetalhamentoDTO atualizar(Long id, AdminAtualizacaoDTO dados) {
        Admin admin = adminRepository.getReferenceById(id);

        admin.atualizar(dados);

        return new AdminDetalhamentoDTO(admin);
    }

    public void inativar(Long id) {
        Admin admin = adminRepository.getReferenceById(id);

        admin.inativar();
    }
}
