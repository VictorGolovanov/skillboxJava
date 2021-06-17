package main.controller;

import main.model.TaskEntity;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Comparator;

@Controller
public class DefaultController {

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping("/")
    public String index(Model model){
        Iterable<TaskEntity> taskEntityIterable = taskRepository.findAll();
        ArrayList<TaskEntity> tasks = new ArrayList<>();
        for(TaskEntity task : taskEntityIterable){
            tasks.add(task);
        }
        // сортируем по id
        tasks.sort(Comparator.comparing(TaskEntity::getId));

        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksCount", tasks.size());


        return "index";
    }
}
