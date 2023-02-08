package med.voll.api.controller;


import med.voll.api.dto.medicos.MedicoDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicosController {

    @PostMapping(value = "/registrar")
    public void registraMedico(@RequestBody MedicoDTO medico) {
        System.out.println(medico);
    }
}
