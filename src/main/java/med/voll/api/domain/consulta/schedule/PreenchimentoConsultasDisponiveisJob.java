package med.voll.api.domain.consulta.schedule;

import java.time.DayOfWeek;
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
import med.voll.api.domain.consulta.dia.Dia;
import med.voll.api.domain.consulta.dia.DiaRepository;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;

@Component
public class PreenchimentoConsultasDisponiveisJob implements Job {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private DiaRepository diaRepository;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LocalDate hoje = LocalDate.now();
		
		for (int i = 0; i <= 31; i++) {
			Dia dia = new Dia();
			
			if (hoje.plusDays(i).getDayOfWeek() == DayOfWeek.SUNDAY) {
				continue;
			}
			if (diaRepository.findByData(hoje.plusDays(i)) != null) {
				continue;
			}
			
			dia.setData(hoje.plusDays(i));
			
			diaRepository.save(dia);
		}
		
		List<Dia> dias = diaRepository.diasNoPresenteOuFuturo(hoje);
		
		medicos().forEach(medico -> {
			dias.forEach(dia -> {
				for (int i = 7; i < 19; i++) {
					LocalDateTime data = dia.getData().atTime(i, 0);
					
					Consulta consulta = new Consulta(null, medico, null, dia, data, ConsultaStatus.DISPONIVEL);
					
					if (consultaRepository.consultaDoMedicoNoDiaHorario(medico.getId(), data) != null) {
						continue;
					}
					
					consultaRepository.save(consulta);
				}
			});
		});
	}
	
	private List<Medico> medicos() {
		return medicoRepository.findAll();
	}
}
