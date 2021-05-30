package main;
import main.model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ToDoListStorage
{
    private static AtomicInteger currentId = new AtomicInteger();
    private static ConcurrentHashMap<Integer, Task> todoList = new ConcurrentHashMap<>();

    public static List<Task> getToDoList(){
        ArrayList<Task> result = new ArrayList<>();
        result.addAll(todoList.values());
        return result;
    }

    public static int addTask(Task task){
        currentId.getAndIncrement();
        int taskId = currentId.get();
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
