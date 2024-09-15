package tech.getarrays.schedulermanager.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.getarrays.schedulermanager.models.Task;


public interface TaskRepo extends JpaRepository<Task, String> {

    void deleteTaskByID(String ID);

    Optional<Task> findTaskByID(String ID);
}


