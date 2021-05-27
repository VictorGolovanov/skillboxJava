package main;

import main.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ToDoListStorage
{
    private static int currentId;
    private static HashMap<Integer, Task> todoList = new HashMap<>();

    public static List<Task> getToDoList(){
        ArrayList<Task> result = new ArrayList<>();
        result.addAll(todoList.values());
        return result;
    }

    public static int addTask(Task task){
        int taskId = currentId++;
        task.setId(taskId);
        todoList.put(taskId, task);
        System.out.println("Deal with id " + taskId + " added");

        return taskId;
    }

    public static void deleteTask(int taskId){
        if(todoList.containsKey(taskId)){
            todoList.remove(taskId);
            System.out.println("Deal with id: " + taskId + " deleted." );
        }
    }

    public static Task getTask(int taskId){
        if(todoList.containsKey(taskId)){
            return todoList.get(taskId);
        }
        return null;
    }


}
