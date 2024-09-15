package tech.getarrays.schedulermanager;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchedulermanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulermanagerApplication.class, args);
	}
}
