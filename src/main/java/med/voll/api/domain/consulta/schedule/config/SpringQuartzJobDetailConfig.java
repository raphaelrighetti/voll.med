package med.voll.api.domain.consulta.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import med.voll.api.domain.consulta.schedule.PreenchimentoConsultasDisponiveisJob;

@Configuration
public class SpringQuartzJobDetailConfig {

	@Bean
	public JobDetailFactoryBean preenchimentoConsultasDisponiveisJobDetail() {
	    JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
	    jobDetailFactory.setJobClass(PreenchimentoConsultasDisponiveisJob.class);
	    jobDetailFactory.setDescription("Preenche tabela de consultas com todas as consultas disponíveis um mês para frente");
	    jobDetailFactory.setDurability(true);
	    return jobDetailFactory;
	}
}
