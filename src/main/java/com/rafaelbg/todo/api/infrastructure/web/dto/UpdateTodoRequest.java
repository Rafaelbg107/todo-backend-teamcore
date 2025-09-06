package com.rafaelbg.todo.api.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateTodoRequest {
    
  @NotBlank(message = "Title is required")
  @Size(max = 255, message = "Title must not exceed 255 characters")
  private String title;
  
  @Size(max = 1000, message = "Description must not exceed 1000 characters")
  private String description;
  
  private java.time.LocalDateTime dueDate;
  
  // Default constructor
  public UpdateTodoRequest() {}
  
  // Constructor
  public UpdateTodoRequest(String title, String description, java.time.LocalDateTime dueDate) {
    this.title = title;
    this.description = description;
    this.dueDate = dueDate;
  }
  
  // Getters and Setters
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public java.time.LocalDateTime getDueDate() {
    return dueDate;
  }
  
  public void setDueDate(java.time.LocalDateTime dueDate) {
    this.dueDate = dueDate;
  }
}