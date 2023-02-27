package med.voll.api.validacao.consulta.abstracao;

import med.voll.api.dto.consulta.ConsultaDTOAgendamento;

public interface ValidacaoAgendamentoConsulta {

    void validar(ConsultaDTOAgendamento dados);
}
