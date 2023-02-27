package med.voll.api.validacao.consulta.agendamento;

import med.voll.api.dto.consulta.ConsultaDTOAgendamento;
import med.voll.api.validacao.consulta.abstracao.ValidacaoAgendamentoConsulta;
import med.voll.api.validacao.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidacaoAntecedenciaDe30Minutos implements ValidacaoAgendamentoConsulta {

    @Override
    public void validar(ConsultaDTOAgendamento dados) {
        if (LocalDateTime.now().plusMinutes(30).isAfter(dados.data())) {
            throw new ValidacaoException("Consultas devem ser agendadas com antecedência mínima de 30 minutos");
        }
    }
}
