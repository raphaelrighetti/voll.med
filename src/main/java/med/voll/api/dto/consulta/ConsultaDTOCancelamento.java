package med.voll.api.dto.consulta;

import jakarta.validation.constraints.NotNull;
import med.voll.api.entity.consulta.ConsultaMotivoCancelamento;

public record ConsultaDTOCancelamento(
        @NotNull
        Long id,
        @NotNull
        ConsultaMotivoCancelamento motivo
) {
}
