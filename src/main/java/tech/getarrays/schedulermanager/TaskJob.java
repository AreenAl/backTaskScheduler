package tech.getarrays.schedulermanager;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(TaskJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Long taskId = context.getJobDetail().getJobDataMap().getLong("taskId");
        logger.info("Task {} is started at {}", taskId, LocalDateTime.now());
        // Add your task execution logic here
        logger.info("Task {} is ended at {}", taskId, LocalDateTime.now());
        System.out.println("Executing Task Job");
    }
}
