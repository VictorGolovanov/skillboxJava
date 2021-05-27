package main;

import main.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoListController
{
    @GetMapping("/tasks/")
    public List<Task> list(){
        return ToDoListStorage.getToDoList();
    }

    @PostMapping("/tasks/")
    public int addTask(Task task){
        return ToDoListStorage.addTask(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable int id){
        ToDoListStorage.deleteTask(id);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id){
        Task task = ToDoListStorage.getTask(id);
        if(task == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }
}
