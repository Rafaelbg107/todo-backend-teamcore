package com.rafaelbg.todo.api.domain.port.output;

import com.rafaelbg.todo.api.domain.model.Todo;
import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    
  // Save a todo (create or update)
  Todo save(Todo todo);
  
  // Find todo by ID
  Optional<Todo> findById(Long id);
  
  // Find all todos
  List<Todo> findAll();
  
  // Find todos by completion status
  List<Todo> findByCompleted(boolean completed);
  
  // Delete todo by ID
  void deleteById(Long id);
  
  // Check if todo exists by ID
  boolean existsById(Long id);
}