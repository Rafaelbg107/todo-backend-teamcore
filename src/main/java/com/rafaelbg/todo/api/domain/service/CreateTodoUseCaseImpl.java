package com.rafaelbg.todo.api.domain.service;

import com.rafaelbg.todo.api.domain.model.Todo;
import com.rafaelbg.todo.api.domain.port.input.CreateTodoUseCase;
import com.rafaelbg.todo.api.domain.port.output.TodoRepository;

import org.springframework.stereotype.Service;

@Service
public class CreateTodoUseCaseImpl implements CreateTodoUseCase {
    
  private final TodoRepository todoRepository;
  
  public CreateTodoUseCaseImpl(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }
  
  @Override
  public Todo createTodo(String title, String description, java.time.LocalDateTime dueDate) {
    // Validate input
    if (title == null || title.trim().isEmpty()) {
      throw new IllegalArgumentException("Title cannot be null or empty");
    }
    
    // Create new todo
    Todo todo = new Todo(title.trim(), description, dueDate);
    
    // Save and return
    return todoRepository.save(todo);
  }
}