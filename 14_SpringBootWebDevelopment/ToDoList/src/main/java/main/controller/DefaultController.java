package main.controller;

import main.model.TaskEntity;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class DefaultController {

    @Autowired
    TaskRepository taskRepository;

    //@Value("${someParameter.value}")
    //private Integer someParameter;

    @RequestMapping
    public String index(Model model){
        Iterable<TaskEntity> taskEntityIterable = taskRepository.findAll();
        ArrayList<TaskEntity> tasks = new ArrayList<>();
        for(TaskEntity task : taskEntityIterable){
            tasks.add(task);
        }

        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksCount", tasks.size());
        //model.addAttribute("someParameter", someParameter);


        return "index";
    }
}
