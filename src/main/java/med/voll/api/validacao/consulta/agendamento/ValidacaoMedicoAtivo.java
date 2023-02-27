package med.voll.api.validacao.consulta.agendamento;

import med.voll.api.dto.consulta.ConsultaDTOAgendamento;
import med.voll.api.entity.medico.Medico;
import med.voll.api.repository.medico.MedicoRepository;
import med.voll.api.validacao.consulta.abstracao.ValidacaoAgendamentoConsulta;
import med.voll.api.validacao.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMedicoAtivo implements ValidacaoAgendamentoConsulta {

    @Autowired
    private MedicoRepository repository;

    @Override
    public void validar(ConsultaDTOAgendamento dados) {
        if (dados.medicoId() == null) {
            return;
        }

        Medico medico = repository.getReferenceById(dados.medicoId());

        if (!medico.getStatus()) {
            throw new ValidacaoException("Medico inativo");
        }
    }
}
