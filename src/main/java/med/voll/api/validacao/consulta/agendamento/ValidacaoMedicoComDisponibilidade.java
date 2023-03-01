package med.voll.api.validacao.consulta.agendamento;

import med.voll.api.dto.consulta.ConsultaDTOAgendamento;
import med.voll.api.repository.consulta.ConsultaRepository;
import med.voll.api.validacao.consulta.abstracao.ValidacaoAgendamentoConsulta;
import med.voll.api.validacao.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMedicoComDisponibilidade implements ValidacaoAgendamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(ConsultaDTOAgendamento dados) {
        if (dados.medicoId() == null) {
            return;
        }

        if (repository.existsByMedicoIdAndDataAndMotivoIsNull(dados.medicoId(), dados.data())) {
            throw new ValidacaoException("Médico sem disponibilidade");
        }
    }
}
