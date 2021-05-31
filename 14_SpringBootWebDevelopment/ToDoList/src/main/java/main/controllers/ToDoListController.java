package main.controllers;
import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoListController
{
    @Autowired
    private TaskRepository repository;

    @GetMapping("/tasks/")
    public List<Task> list(){
        Iterable<Task> tasksIterable = repository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for(Task task : tasksIterable){
            tasks.add(task);
        }
        return tasks;
    }

    @PostMapping("/tasks/")
    public int addTask(Task task){
        Task newTask = repository.save(task);
        return newTask.getId();
    }

    @PostMapping("/tasks/{id}")
    public ResponseEntity updateTaskById(@PathVariable int id, Task newTask){
        Optional<Task> taskOptional = repository.findById(id);
        if(taskOptional.isPresent()){
            newTask.setId(id);
            repository.save(newTask);
            return new ResponseEntity(taskOptional, HttpStatus.OK);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable int id){
        repository.deleteById(id);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id){
        Optional<Task> taskOptional = repository.findById(id);
        if(taskOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(taskOptional, HttpStatus.OK);
    }
}
