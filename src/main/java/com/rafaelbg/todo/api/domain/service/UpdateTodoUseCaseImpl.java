package com.rafaelbg.todo.api.domain.service;

import com.rafaelbg.todo.api.domain.model.Todo;
import com.rafaelbg.todo.api.domain.port.input.UpdateTodoUseCase;
import com.rafaelbg.todo.api.domain.port.output.TodoRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UpdateTodoUseCaseImpl implements UpdateTodoUseCase {
  
  private final TodoRepository todoRepository;
  
  public UpdateTodoUseCaseImpl(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }
  
  @Override
  public Optional<Todo> updateTodo(Long id, String title, String description, LocalDateTime dueDate) {
    if (id == null || id <= 0) {
      throw new IllegalArgumentException("ID must be a positive number");
    }
    
    if (title == null || title.trim().isEmpty()) {
      throw new IllegalArgumentException("Title cannot be null or empty");
    }
    
    return todoRepository.findById(id)
      .map(todo -> {
        todo.setTitle(title.trim());
        todo.setDescription(description);
        todo.setDueDate(dueDate);
        return todoRepository.save(todo);
      });
  }
  
  @Override
  public Optional<Todo> markTodoAsCompleted(Long id) {
    if (id == null || id <= 0) {
      throw new IllegalArgumentException("ID must be a positive number");
    }
    
    return todoRepository.findById(id)
      .map(todo -> {
        todo.markAsCompleted();
        return todoRepository.save(todo);
      });
  }
  
  @Override
  public Optional<Todo> markTodoAsPending(Long id) {
    if (id == null || id <= 0) {
      throw new IllegalArgumentException("ID must be a positive number");
    }
    
    return todoRepository.findById(id)
      .map(todo -> {
          todo.markAsPending();
          return todoRepository.save(todo);
      });
  }
}