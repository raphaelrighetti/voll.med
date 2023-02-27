package med.voll.api.dto.consulta;

import med.voll.api.entity.consulta.Consulta;
import med.voll.api.entity.consulta.ConsultaMotivoCancelamento;
import med.voll.api.entity.consulta.ConsultaStatus;
import med.voll.api.entity.medico.MedicoEspecialidade;

import java.time.LocalDateTime;

public record ConsultaDTODetalhamento(
        Long id,
        Long medicoId,
        MedicoEspecialidade especialidade,
        Long pacienteId,
        LocalDateTime data,
        ConsultaStatus status,
        ConsultaMotivoCancelamento motivoCancelamento
) {
    public ConsultaDTODetalhamento(Consulta consulta) {
        this(
                consulta.getId(),
                consulta.getMedico().getId(),
                consulta.getMedico().getEspecialidade(),
                consulta.getPaciente().getId(),
                consulta.getData(),
                consulta.getStatus(),
                consulta.getMotivo()
        );
    }
}
