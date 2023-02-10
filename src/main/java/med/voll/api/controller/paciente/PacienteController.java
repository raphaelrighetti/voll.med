package med.voll.api.controller.paciente;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.paciente.PacienteDTOCadastro;
import med.voll.api.dto.paciente.PacienteDTOListagem;
import med.voll.api.entity.paciente.Paciente;
import med.voll.api.repository.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void registraPaciente(@RequestBody @Valid PacienteDTOCadastro paciente) {
        repository.save(new Paciente(paciente));
    }

    @GetMapping
    public Page<PacienteDTOListagem> listar(@PageableDefault(sort = {"nome"}) Pageable pageable) {
        return repository.findAll(pageable).map(PacienteDTOListagem::new);
    }
}
