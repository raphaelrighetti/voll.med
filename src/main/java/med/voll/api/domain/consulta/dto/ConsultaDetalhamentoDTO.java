package med.voll.api.domain.consulta.dto;

import java.time.LocalDateTime;

import med.voll.api.domain.consulta.Consulta;

public record ConsultaDetalhamentoDTO(
		Long id,
		Long medicoId,
		String medicoNome,
		Long pacienteId,
		String pacienteNome,
		LocalDateTime data
) {
	public ConsultaDetalhamentoDTO(Consulta consulta) {
		this(
				consulta.getId(),
				consulta.getMedico().getId(),
				consulta.getMedico().getNome(),
				consulta.getPaciente().getId(),
				consulta.getPaciente().getNome(),
				consulta.getData()
		);
	}
}
