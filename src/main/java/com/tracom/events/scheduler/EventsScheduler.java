package com.tracom.events.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	/**
	 @Scheduled(fixedRate = 9000L)
	 void PrintingDate(){
	 System.out.println("Today is " + new Date());
	 }
	 **/

/**	@Scheduled(initialDelay = 1000l,fixedDelay = 2000L)
	void PrintingDate() throws InterruptedException{
		System.out.println("Today is " + new Date());
		Thread.sleep(1000l);
	} **/

/**	@Scheduled(initialDelay = 1000l,fixedDelayString = "PT1M")
	void PrintingDate() throws InterruptedException {
		System.out.println("Today is " + new Date());
		Thread.sleep(1000l);
	} */


/**	@Scheduled(initialDelay = 1000l, fixedDelayString = "${task.delay}") //go to app.properties file and add: task.delay=PT5S or PT5M or PT1H etc..
	void PrintingDate() throws InterruptedException{
		System.out.println("Today is " + new Date());
		Thread.sleep(1000l);
	} */
    private static final Logger logger = LoggerFactory.getLogger(EventsScheduler.class);

	@Scheduled(cron = "*/5 23  11 * * MON-FRI")                     //CRONTAB.GURU for more help
	void PrintingDate() throws InterruptedException{
		logger.info("Today is " + new Date());
		Thread.sleep(1000l);
	}
	@Scheduled(cron = "*/5 23  11 * * MON-FRI")                     //spring.task.scheduling.pool.size=10
	void PrintingDate2() throws InterruptedException{
		logger.info("2nd Today is " + new Date());
		Thread.sleep(1000l);
	}

	@Configuration
	@EnableScheduling
	@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
	class SchedulingConfiguration{

	}
}

