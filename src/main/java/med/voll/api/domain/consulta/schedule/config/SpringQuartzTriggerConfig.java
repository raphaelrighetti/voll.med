package med.voll.api.domain.consulta.schedule.config;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class SpringQuartzTriggerConfig {

	@Bean
	public SimpleTriggerFactoryBean trigger(JobDetail job) {
	    SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
	    trigger.setJobDetail(job);
	    trigger.setRepeatInterval(3555000);
	    trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
	    return trigger;
	}
}
