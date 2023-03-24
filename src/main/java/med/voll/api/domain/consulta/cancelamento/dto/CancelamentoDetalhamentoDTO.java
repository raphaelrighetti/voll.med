package med.voll.api.domain.consulta.cancelamento.dto;

import java.time.LocalDateTime;

import med.voll.api.domain.consulta.cancelamento.Cancelamento;
import med.voll.api.domain.consulta.cancelamento.MotivoCancelamento;

public record CancelamentoDetalhamentoDTO(
		Long id,
		Long consultaId,
		Long medicoId,
		String medicoNome,
		Long pacienteId,
		String pacienteNome,
		LocalDateTime data,
		MotivoCancelamento motivo
) {
	public CancelamentoDetalhamentoDTO(Cancelamento cancelamento) {
		this(
				cancelamento.getId(),
				cancelamento.getConsulta().getId(),
				cancelamento.getConsulta().getMedico().getId(),
				cancelamento.getConsulta().getMedico().getNome(),
				cancelamento.getPaciente().getId(),
				cancelamento.getPaciente().getNome(),
				cancelamento.getConsulta().getData(),
				cancelamento.getMotivo()
		);
	}
}
