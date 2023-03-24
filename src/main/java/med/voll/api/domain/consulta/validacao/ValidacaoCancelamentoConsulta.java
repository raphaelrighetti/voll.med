package med.voll.api.domain.consulta.validacao;

import med.voll.api.domain.consulta.cancelamento.dto.CancelamentoDTO;

public interface ValidacaoCancelamentoConsulta {
	
	void validar(CancelamentoDTO dados);
}
