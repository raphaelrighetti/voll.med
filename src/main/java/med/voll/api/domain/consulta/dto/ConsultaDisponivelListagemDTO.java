package med.voll.api.domain.consulta.dto;

import java.time.LocalDateTime;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaStatus;

public record ConsultaDisponivelListagemDTO(
		Long id,
		Long medicoId,
		LocalDateTime data,
		ConsultaStatus status
) {
	public ConsultaDisponivelListagemDTO(Consulta consulta) {
		this(
				consulta.getId(),
				consulta.getMedico().getId(),
				consulta.getData(),
				consulta.getStatus()
		);
	}
}
