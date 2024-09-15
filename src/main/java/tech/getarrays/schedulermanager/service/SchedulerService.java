package tech.getarrays.schedulermanager.service;

import java.util.List;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tech.getarrays.schedulermanager.QuartzConfig;
import tech.getarrays.schedulermanager.exception.UserNotFoundException;
import tech.getarrays.schedulermanager.models.Task;
import tech.getarrays.schedulermanager.repo.TaskRepo;

@Service
@Transactional
public class SchedulerService {
    private final TaskRepo taskRepo;
    private static final String[] PREFIXES = {"abc", "def", "ghi", "jkl", "mno"};
    private static int counter = 1;
    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private QuartzConfig quartzConfig;
    @Autowired
    public SchedulerService(TaskRepo taskRepo){
        this.taskRepo=taskRepo;
    }


    public Task addTask(Task task) {
//        if (isOverlapping(task)) {
//            logger.error("Task overlaps with existing tasks: {}", task);
//            throw new IllegalArgumentException("Task overlaps with existing tasks.");
//        }
        String id = generateCustomId();
        task.setID(id);
        Task savedTask = taskRepo.save(task);
        scheduleTask(task);
        return taskRepo.save(task);
    }

    public List<Task> findAllTasks() {
        return taskRepo.findAll();
    }
    public Task updateTask(Task task) {
        return taskRepo.save(task);
    }
    public Task findTaskById(String id) {
        return taskRepo.findTaskByID(id)
                .orElseThrow(() -> new UserNotFoundException("Task not found with ID: " + id));
    }
    public void deleteTask(String id) {
        taskRepo.deleteTaskByID(id);
    }
    private String generateCustomId() {
        String prefix = PREFIXES[counter % PREFIXES.length];
        String suffix = String.format("%02d", counter + 1); // Ensures two digits
        counter++;
        return prefix + suffix;
    }
    private boolean isOverlapping(Task newTask) {
        List<Task> tasks = taskRepo.findAll();
        for (Task task : tasks) {
            if (task.getID().equals(newTask.getID())) {
                continue; // Skip the task being updated
            }
            if (task.getEndTime().isAfter(newTask.getStartTime()) && task.getStartTime().isBefore(newTask.getEndTime())) {
                return true; // Overlapping detected
            }
        }
        return false;
    }
    public void scheduleTask(Task task) {
        try {
            System.out.println("exeeeeeeeeeeeec");
            System.out.println(task);
            quartzConfig.scheduleTask(task, scheduler);
            logger.info("Task {} scheduled successfully", task.getID());
        } catch (SchedulerException e) {
            logger.error("Error scheduling task {}: {}", task.getID(), e.getMessage());
        }
    }

}
