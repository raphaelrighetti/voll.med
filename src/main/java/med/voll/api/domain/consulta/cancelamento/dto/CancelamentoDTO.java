package med.voll.api.domain.consulta.cancelamento.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.consulta.cancelamento.MotivoCancelamento;

public record CancelamentoDTO(
		@NotNull
		Long consultaId,
		@NotNull
		MotivoCancelamento motivo
) {

}
