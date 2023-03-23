package med.voll.api.domain.consulta.dto;

import jakarta.validation.constraints.NotNull;

public record ConsultaAgendamentoDTO(
		@NotNull
		Long id,
		@NotNull
		Long pacienteId
) {

}
