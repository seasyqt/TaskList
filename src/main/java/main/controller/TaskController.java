package main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import main.model.Task;
import main.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class TaskController {

  @Autowired
  private TaskRepository taskRepository;

  @GetMapping("/task/")
  public List<Task> list() {
    List listTask = new ArrayList<Task>();
    taskRepository
        .findAll()
        .forEach(l -> listTask.add(l));
    return listTask;
  }

  @GetMapping("/task/{id}")
  public ResponseEntity get(@PathVariable int id) {
    Optional<Task> taskOptional = taskRepository.findById(id);
    if (!taskOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return new ResponseEntity(taskOptional.get(), HttpStatus.OK);

  }

  @PostMapping("/task/")
  public int add(Task task) {
    return taskRepository.save(task).getId();
  }

  @PostMapping("/task/{id}")
  public ResponseEntity add(@PathVariable int id) {
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
  }

  @PutMapping("/task/")
  public ResponseEntity updateAll(Task task) {
    List list = new ArrayList<Task>();
    taskRepository
        .findAll()
        .forEach(line -> list.add(Task.cloneTask(line.getId(), task)));
    return new ResponseEntity(list, HttpStatus.OK);
  }

  @PutMapping("/task/{id}")
  public ResponseEntity update(@PathVariable int id, Task task) {
    Optional<Task> taskOptional = taskRepository.findById(id);
    final boolean isUpdate = taskOptional.isPresent();
    if (isUpdate) {
      taskRepository.save(Task.cloneTask(id, task));
    }
    return isUpdate
        ? new ResponseEntity(HttpStatus.OK)
        : new ResponseEntity(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/task/")
  public void deleteAll() {
    taskRepository.deleteAll();
  }

  @DeleteMapping("/task/{id}")
  public ResponseEntity deleteToDo(@PathVariable int id) {
    Optional<Task> taskOptional = taskRepository.findById(id);
    final boolean isDelete = taskOptional.isPresent();
    if (isDelete) {
      taskRepository.deleteById(id);
    }
    return isDelete
        ? new ResponseEntity(HttpStatus.OK)
        : new ResponseEntity(HttpStatus.NOT_FOUND);
  }

}
