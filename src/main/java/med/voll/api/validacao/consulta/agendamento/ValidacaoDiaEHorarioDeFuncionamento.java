package med.voll.api.validacao.consulta.agendamento;

import med.voll.api.dto.consulta.ConsultaDTOAgendamento;
import med.voll.api.validacao.consulta.abstracao.ValidacaoAgendamentoConsulta;
import med.voll.api.validacao.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidacaoDiaEHorarioDeFuncionamento implements ValidacaoAgendamentoConsulta {

    @Override
    public void validar(ConsultaDTOAgendamento dados) {
        boolean ehDomingo = dados.data().getDayOfWeek() == DayOfWeek.SUNDAY;
        boolean ehAntesDaAbertura = dados.data().getHour() < 7;
        boolean ehDepoisDoFechamento = dados.data().getHour() > 18;

        if (ehDomingo || ehAntesDaAbertura || ehDepoisDoFechamento) {
            throw new ValidacaoException("Fora dos dias/horários de funcionamento");
        }
    }
}
