package med.voll.api.domain.consulta.dto;

import java.time.LocalDateTime;

import med.voll.api.domain.consulta.Consulta;

public record ConsultaDetalhamentoDTO(
		Long id,
		Long medicoId,
		Long pacienteId,
		LocalDateTime data
) {
	public ConsultaDetalhamentoDTO(Consulta consulta) {
		this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
	}
}
