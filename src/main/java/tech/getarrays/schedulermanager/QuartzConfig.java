package tech.getarrays.schedulermanager;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.getarrays.schedulermanager.models.Task;

import java.util.Date;

@Configuration
public class QuartzConfig {

    @Bean
    public SchedulerFactory schedulerFactory() {
        return new StdSchedulerFactory();
    }

    @Bean
    public Scheduler scheduler(SchedulerFactory schedulerFactory) throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        return scheduler;
    }

    // Method to schedule tasks
    public void scheduleTask(Task task, Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(TaskJob.class)
                .withIdentity("taskJob" + task.getID(), "taskGroup")
                .usingJobData("taskId", task.getID())
                .build();

        int frequencyInDays = task.getfrequency(); // Assuming frequency is given in days

        // Calculate the next execution time from the start time
        Date startTime = java.sql.Timestamp.valueOf(task.getStartTime());

        // Create a CalendarIntervalTrigger for interval-based scheduling
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("taskTrigger" + task.getID(), "taskGroup")
                .startAt(startTime) // Set the start time
                .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
                        .withInterval(frequencyInDays, DateBuilder.IntervalUnit.DAY)) // Run every `frequencyInDays` days
                .build();

        // Log details for debugging
        System.out.println("Scheduling task with frequency of " + frequencyInDays + " days.");
        System.out.println("Start time: " + task.getStartTime());

        // Schedule the job
        scheduler.scheduleJob(jobDetail, trigger);
    }
}