package main.service;

import main.model.TaskEntity;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService
{
    @Autowired
    private TaskRepository repository;

    public List<TaskEntity> list(){
        Iterable<TaskEntity> tasksIterable = repository.findAll();
        ArrayList<TaskEntity> taskEntities = new ArrayList<>();
        for(TaskEntity taskEntity : tasksIterable){
            taskEntities.add(taskEntity);
        }
        return taskEntities;
    }

    public int addTask(TaskEntity taskEntity){
        TaskEntity newTaskEntity = repository.save(taskEntity);
        return newTaskEntity.getId();
    }


    public ResponseEntity updateTaskById(int id, TaskEntity newTaskEntity){
        Optional<TaskEntity> taskOptional = repository.findById(id);
        if(taskOptional.isPresent()){
            newTaskEntity.setId(id);
            repository.save(newTaskEntity);
            return new ResponseEntity(taskOptional, HttpStatus.OK);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    public void delete(int id){
        repository.deleteById(id);
    }

    public ResponseEntity get(int id){
        Optional<TaskEntity> taskOptional = repository.findById(id);
        if(taskOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(taskOptional, HttpStatus.OK);
    }
}
