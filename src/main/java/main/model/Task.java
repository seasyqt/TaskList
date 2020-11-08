package main.model;

import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String name;
  private String descriptionName;

  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createdDate;
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date completionDate;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescriptionName() {
    return descriptionName;
  }

  public void setDescriptionName(String descriptionName) {
    this.descriptionName = descriptionName;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = new Date(createdDate);
  }

  public Date getCompletionDate() {
    return completionDate;
  }

  public void setCompletionDate(Date completionDate) {
    this.completionDate = completionDate;
  }

  public void setCompletionDate(String completionDate) {
    this.completionDate = new Date(completionDate);
  }

  public static Task cloneTask(int newId, Task task) {
    Task taskChange = new Task();
    taskChange.setCompletionDate(task.getCompletionDate());
    taskChange.setCreatedDate(task.getCreatedDate());
    taskChange.setDescriptionName(task.getDescriptionName());
    taskChange.setId(newId);
    taskChange.setName(task.getName());
    return taskChange;
  }
}
