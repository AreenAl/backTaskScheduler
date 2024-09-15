package tech.getarrays.schedulermanager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tech.getarrays.schedulermanager.models.Task;
import tech.getarrays.schedulermanager.service.SchedulerService;

import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from this origin
public class SchedulerResource {
    @Autowired
    private SchedulerService schedulerService;

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks () {
        List<Task> tasks = schedulerService.findAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/logs")
    public void getAllLogs () {
        System.out.println("hhhhhhjjjj");

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Task> getTaskById (@PathVariable("id") String id) {
        Task task = schedulerService.findTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        System.out.println("Received Task: "+task);
        Task newTask = schedulerService.addTask(task);
        schedulerService.scheduleTask(task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        Task updatedTask = schedulerService.updateTask(task);
        schedulerService.scheduleTask(updatedTask);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") String id) {
        schedulerService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
