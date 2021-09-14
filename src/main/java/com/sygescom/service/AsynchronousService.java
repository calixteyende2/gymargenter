package com.sygescom.service;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sygescom.entities.Notification;
import com.sygescom.repository.NotificationRepository;

@Service
public class AsynchronousService {
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	
	private final static Logger LOGGER = LoggerFactory.getLogger(AsynchronousService.class);
	
	//@PostConstruct
	//@Scheduled(fixedDelay=3000)
    @Async("asyncTaskExecutor")
    public void processData(Notification notification) throws InterruptedException
    {
    	TimeUnit.SECONDS.sleep(3);
        LOGGER.info("\nStart processing");
        TimeUnit.SECONDS.sleep(5);
		ScheduledExecutorService schedulerExecutor = Executors.newScheduledThreadPool(1);
		Future<?>scheduledFuture = schedulerExecutor.scheduleWithFixedDelay(()->{
					notificationRepository.save(notification);
					LOGGER.info("\nNotification done!");
				}, 5, 5, TimeUnit.SECONDS);
		try {
			scheduledFuture.get(5, TimeUnit.SECONDS);
		}catch (Exception e) {
			schedulerExecutor.awaitTermination(5, TimeUnit.SECONDS);
			schedulerExecutor.shutdownNow();
			scheduledFuture.cancel(true);
		} 
    }
}
