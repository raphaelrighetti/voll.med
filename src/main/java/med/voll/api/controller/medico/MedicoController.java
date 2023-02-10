package med.voll.api.controller.medico;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.medico.MedicoDTO;
import med.voll.api.entity.medico.Medico;
import med.voll.api.repository.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    MedicoRepository repository;

    @PostMapping(value = "/registrar")
    @Transactional
    public void registraMedico(@RequestBody @Valid MedicoDTO medico) {
        repository.save(new Medico(medico));
    }
}
