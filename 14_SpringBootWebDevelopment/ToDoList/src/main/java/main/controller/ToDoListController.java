package main.controller;
import main.model.TaskEntity;
import main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class ToDoListController
{
    private final TaskService taskService;

    @Autowired
    public ToDoListController(TaskService taskService) {
        Objects.requireNonNull(taskService);
        this.taskService = taskService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    @GetMapping("/tasks/")
    public List<TaskEntity> list(){
        return getTaskService().list();
    }

    @PostMapping("/tasks/")
    public int addTask(TaskEntity taskEntity){
        return getTaskService().addTask(taskEntity);
    }

    @PostMapping("/tasks/{id}")
    public ResponseEntity updateTaskById(@PathVariable int id, TaskEntity newTaskEntity){
        return getTaskService().updateTaskById(id, newTaskEntity);
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable int id){
        getTaskService().delete(id);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id){
        return getTaskService().get(id);
    }
}
