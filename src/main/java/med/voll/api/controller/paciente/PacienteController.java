package med.voll.api.controller.paciente;

import jakarta.validation.Valid;
import med.voll.api.dto.paciente.PacienteDTO;
import med.voll.api.entity.paciente.Paciente;
import med.voll.api.repository.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping(value = "/registrar")
    public void registraPaciente(@RequestBody @Valid PacienteDTO paciente) {
        repository.save(new Paciente(paciente));
    }
}
