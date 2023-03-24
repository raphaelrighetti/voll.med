package med.voll.api.domain.consulta.dto;

import java.time.LocalDateTime;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaStatus;

public record ConsultaListagemDTO(
		Long id,
		Long medicoId,
		Long pacienteId,
		LocalDateTime data,
		ConsultaStatus status
) {
	public ConsultaListagemDTO(Consulta consulta) {
		this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData(), consulta.getStatus());
	}
}
