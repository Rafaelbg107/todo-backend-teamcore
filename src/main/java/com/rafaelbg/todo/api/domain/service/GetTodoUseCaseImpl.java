package com.rafaelbg.todo.api.domain.service;

import com.rafaelbg.todo.api.domain.model.Todo;
import com.rafaelbg.todo.api.domain.port.input.GetTodoUseCase;
import com.rafaelbg.todo.api.domain.port.output.TodoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetTodoUseCaseImpl implements GetTodoUseCase {
    
  private final TodoRepository todoRepository;
  
  public GetTodoUseCaseImpl(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }
  
  @Override
  public Optional<Todo> getTodoById(Long id) {
    if (id == null || id <= 0) {
      throw new IllegalArgumentException("ID must be a positive number");
    }
    return todoRepository.findById(id);
  }
  
  @Override
  public List<Todo> getAllTodos() {
    return todoRepository.findAll();
  }
  
  @Override
  public List<Todo> getTodosByStatus(boolean completed) {
    return todoRepository.findByCompleted(completed);
  }
}