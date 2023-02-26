package med.voll.api.dto.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaDTOAgendamento(
        Long medicoId,
        @NotNull
        Long pacienteId,
        @NotNull @Future
        LocalDateTime data
) {
}
