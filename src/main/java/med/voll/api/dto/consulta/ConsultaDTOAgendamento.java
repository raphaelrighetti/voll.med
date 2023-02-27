package med.voll.api.dto.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.entity.medico.MedicoEspecialidade;

import java.time.LocalDateTime;

public record ConsultaDTOAgendamento(
        Long medicoId,
        MedicoEspecialidade especialidade,
        @NotNull
        Long pacienteId,
        @NotNull @Future
        LocalDateTime data
) {
}
