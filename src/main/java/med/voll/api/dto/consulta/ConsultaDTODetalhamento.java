package med.voll.api.dto.consulta;

import java.time.LocalDateTime;

public record ConsultaDTODetalhamento(Long id, Long medicoId, Long pacienteId, LocalDateTime data) {
}
