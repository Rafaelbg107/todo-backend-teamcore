package com.rafaelbg.todo.api.domain.service;

import org.springframework.stereotype.Service;

import com.rafaelbg.todo.api.domain.port.input.DeleteTodoUseCase;
import com.rafaelbg.todo.api.domain.port.output.TodoRepository;

@Service
public class DeleteTodoUseCaseImpl implements DeleteTodoUseCase {
    
  private final TodoRepository todoRepository;
  
  public DeleteTodoUseCaseImpl(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }
  
  @Override
  public boolean deleteTodo(Long id) {
    if (id == null || id <= 0) {
      throw new IllegalArgumentException("ID must be a positive number");
    }
    
    if (todoRepository.existsById(id)) {
      todoRepository.deleteById(id);
      return true;
    }
    return false;
  }
}