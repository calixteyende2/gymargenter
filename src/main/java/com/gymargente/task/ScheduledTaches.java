package com.gymargente.task;


import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTaches {
	
	/**
	 * Generate a CRON expression is a string comprising 6 or 7 fields separated by white space.
	 *
	 * @param seconds    mandatory = yes. allowed values = {@code  0-59    * / , -}
	 * @param minutes    mandatory = yes. allowed values = {@code  0-59    * / , -}
	 * @param hours      mandatory = yes. allowed values = {@code 0-23   * / , -}
	 * @param dayOfMonth mandatory = yes. allowed values = {@code 1-31  * / , - ? L W}
	 * @param month      mandatory = yes. allowed values = {@code 1-12 or JAN-DEC    * / , -}
	 * @param dayOfWeek  mandatory = yes. allowed values = {@code 0-6 or SUN-SAT * / , - ? L #}
	 * @return a CRON Formatted String.
	 */
	//@SuppressWarnings("unused")
	public static String generateCronExpression(final String seconds, final String minutes, final String hours,
            final String dayOfMonth,
            final String month, final String dayOfWeek)
	{
	return String.format("%1$s %2$s %3$s %4$s %5$s %6$s", seconds, minutes, hours, dayOfMonth, month, dayOfWeek);
	}
	
	DateTime dateTime1 = new DateTime();
	String  s = String.valueOf(dateTime1.getSecondOfMinute());
	String  m = String.valueOf(dateTime1.getMinuteOfHour());
	String  h = String.valueOf(dateTime1.getHourOfDay());
	String  d1 = String.valueOf(dateTime1.getDayOfMonth());
	String  m1 = String.valueOf(dateTime1.getMonthOfYear()+1);
	
	@Scheduled(cron = "*/2 * * * * ?")
	public void doSomething1() {
		String cron1 = ScheduledTaches.generateCronExpression("*", "*", "*", "*", "*", "*");		
		//System.out.println("cron1 = " + cron1);
				
	}
	//@Scheduled(initialDelay = 1000, fixedDelay = 60000)	 //initialDelay is optional
	//@Scheduled(initialDelay = 1000, fixedRate = 60000)     //initialDelay is optional
	@Scheduled(fixedDelay = 3000)
	public void doSomething2() {
		String cron2 = ScheduledTaches.generateCronExpression(s, m, h, d1, m1, "*");		
		//System.out.println("cron2 = " + cron2);
		
	}
		
	
	/*  Cron  Special  Strings
	  
	    @reboot – run once at the startup
		@yearly or @annualy – run once a year
		@monthly – run once a month
		@weekly – run once a week
		@daily or @midnight – run once a day
		@hourly – run hourly
		
		@Scheduled(cron = "@hourly") = 	every hour
		
		@Scheduled(cron = "0 15 10 15 * ?")
		
		@yearly (or @annually)  		once a year (0 0 0 1 1 *)
        @monthly   						once a month (0 0 0 1 * *)
        @weekly    						once a week (0 0 0 * * 0)
        @daily (or @midnight)   		once a day (0 0 0 * * *), or
        @hourly  						once an hour, (0 0 * * * *)
        
	 
	
		┌───────────── second (0-59)
		│ ┌───────────── minute (0 - 59)
		│ │ ┌───────────── hour (0 - 23)
		│ │ │ ┌───────────── day of the month (1 - 31)
		│ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
		│ │ │ │ │ ┌───────────── day of the week (0 - 7)
		│ │ │ │ │ │          (or MON-SUN -- 0 or 7 is Sunday)
		│ │ │ │ │ │
		* * * * * *
	
        For the Quartz CronTrigger format, you'd be looking at something like:	
		0 0 6 6 9 ? 2010
		| | | | | |   |
		| | | | | |   +- 2010 only.
		| | | | | +----- any day of the week.
		| | | | +------- 9th month (September).
		| | | +--------- 6th day of the month.
		| | +----------- 6th hour of the day.
		| +------------- Top of the hour (minutes = 0).
		+--------------- Top of the minute (seconds = 0).
	
	*/
	

	
}
