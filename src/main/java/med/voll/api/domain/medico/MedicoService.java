package med.voll.api.domain.medico;

import med.voll.api.domain.medico.dto.MedicoAtualizacaoDTO;
import med.voll.api.domain.medico.dto.MedicoCadastroDTO;
import med.voll.api.domain.medico.dto.MedicoDetalhamentoDTO;
import med.voll.api.domain.medico.dto.MedicoListagemDTO;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.domain.usuario.UsuarioRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    
    public List<LocalDateTime> listarHorariosDisponiveis(Long id) {
    	List<LocalDateTime> horariosOcupados = medicoRepository.horariosOcupadosDoMedico(id);
    	List<LocalDateTime> horariosDisponiveis = getHorarios();
    	
    	horariosDisponiveis.removeAll(horariosOcupados);
    	
    	return Collections.unmodifiableList(horariosDisponiveis);
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
    
    private List<LocalDateTime> getHorarios() {
    	List<LocalDateTime> horarios = new ArrayList<>();
    	
    	for (int i = 7; i < 19; i++) {
    		LocalDateTime horario = LocalDate.now().atTime(i, 0);
    		
    		horarios.add(horario);
    	}
    	
    	return horarios;
    }
}
