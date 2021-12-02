package com.tracom.events.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@SpringBootApplication
public class EventsScheduler {

	public static void main(String[] args) {
		SpringApplication.run(EventsScheduler.class, args);
	}

	@Scheduled(fixedRate = 5000L)
	void PrintingDate(){
		System.out.println("Today is " + new Date());
	}

	@Configuration
	@EnableScheduling
	@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
	class SchedulingConfiguration{

	}
}
