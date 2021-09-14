package com.gymargente;

import java.util.concurrent.Executor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;



@SpringBootApplication(scanBasePackages = {"com.sygescom"})
@EnableScheduling
@EnableAsync
public class GYMARGENTEApplication implements CommandLineRunner {
	
    @Bean(name = "asyncTaskExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsynchTaskThread-");
        executor.initialize();
        return executor;
    }
    

	
    
	public static void main(String[] args) {
		SpringApplication.run(GYMARGENTEApplication.class, args);
		
	

		
	}


	@Override
	public void run(String... args) throws Exception {
		
	}
	

}
