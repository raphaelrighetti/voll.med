package med.voll.api.entity.consulta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.entity.medico.Medico;
import med.voll.api.entity.paciente.Paciente;

import java.time.LocalDateTime;

@Table(name = "consulta")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    private LocalDateTime data;
    @Enumerated(EnumType.STRING)
    private ConsultaStatus status;
    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private ConsultaMotivoCancelamento motivo;

    public void cancelar(ConsultaMotivoCancelamento motivo) {
        status = ConsultaStatus.CANCELADA;
        this.motivo = motivo;
    }

    public void finalizar() {
        status = ConsultaStatus.FINALIZADA;
    }
}
