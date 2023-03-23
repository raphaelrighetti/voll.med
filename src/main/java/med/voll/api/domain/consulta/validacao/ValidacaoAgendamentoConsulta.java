package med.voll.api.domain.consulta.validacao;

import med.voll.api.domain.consulta.dto.ConsultaAgendamentoDTO;

public interface ValidacaoAgendamentoConsulta {

	void validar(ConsultaAgendamentoDTO dados);
}
