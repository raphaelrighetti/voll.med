package med.voll.api.domain.consulta.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.ConsultaStatus;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;

@Component
public class PreenchimentoConsultasDisponiveisJob implements Job {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private ConsultaRepository consultaRepository;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LocalDate hoje = LocalDate.now();
		
		medicos().forEach(medico -> {
			for (int i = 0; i <= 31; i++) {
				for (int j = 7; j < 19; j++) {
					LocalDateTime diaHorario = hoje.plusDays(i).atTime(j, 0);
					Consulta consulta  = consultaRepository.consultaDoMedicoNoDiaHorario(medico.getId(), diaHorario);
					
					if (consulta != null) continue;
					
					Consulta novaConsulta = new Consulta(null, medico, null, diaHorario, ConsultaStatus.DISPONIVEL);
					consultaRepository.save(novaConsulta);
					
					System.out.println(consulta);
					System.out.println(hoje.plusDays(i).atTime(j, 0));
				}
			}
		});
	}
	
	private List<Medico> medicos() {
		return medicoRepository.findAll();
	}
}
