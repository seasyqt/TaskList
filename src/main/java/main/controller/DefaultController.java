package main.controller;

import java.util.ArrayList;
import main.model.Task;
import main.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

  @Autowired
  private TaskRepository taskRepository;

  @RequestMapping("/")
  public String index(Model model) {

    Iterable<Task> tasks = taskRepository.findAll();
    ArrayList<Task> taskList = new ArrayList<>();
    tasks.forEach(l -> taskList.add(l));
    model.addAttribute("task", taskList);
    model.addAttribute("taskCount", taskList.size());

    return "index";
  }

}
