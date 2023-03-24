package med.voll.api.domain.consulta.cancelamento.dto;

import java.time.LocalDateTime;

import med.voll.api.domain.consulta.cancelamento.Cancelamento;
import med.voll.api.domain.consulta.cancelamento.MotivoCancelamento;

public record CancelamentoListagemDTO(
		Long id,
		Long consultaId,
		Long pacienteId,
		LocalDateTime data,
		MotivoCancelamento motivo
) {
	public CancelamentoListagemDTO(Cancelamento cancelamento) {
		this(
				cancelamento.getId(),
				cancelamento.getConsulta().getId(),
				cancelamento.getPaciente().getId(),
				cancelamento.getConsulta().getData(),
				cancelamento.getMotivo()
		);
	}
}
