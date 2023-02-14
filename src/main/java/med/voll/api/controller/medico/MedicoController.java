package med.voll.api.controller.medico;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.medico.MedicoDTOAtualizacao;
import med.voll.api.dto.medico.MedicoDTOCadastro;
import med.voll.api.dto.medico.MedicoDTOListagem;
import med.voll.api.entity.medico.Medico;
import med.voll.api.repository.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    MedicoRepository repository;

    @PostMapping
    @Transactional
    public void registrar(@RequestBody @Valid MedicoDTOCadastro dados) {
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<MedicoDTOListagem> listar(@PageableDefault(sort = {"nome"}) Pageable pageable) {
        return repository.findByStatusTrue(pageable);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid MedicoDTOAtualizacao dados) {
        Optional<Medico> medico = repository.findById(dados.id());
        medico.ifPresent(entity -> entity.atualizarCampos(dados));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        Optional<Medico> medico = repository.findById(id);
        medico.ifPresent(Medico::inativar);
    }
}
