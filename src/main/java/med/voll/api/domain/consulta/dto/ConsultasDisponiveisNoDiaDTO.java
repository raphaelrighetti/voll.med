package med.voll.api.domain.consulta.dto;

import java.util.List;

public record ConsultasDisponiveisNoDiaDTO(
		Long diaId,
		List<ConsultaDisponivelListagemDTO> consultas
) {

}
