package com.rafaelbg.todo.api.domain.port.input;

import com.rafaelbg.todo.api.domain.model.Todo;
import java.util.List;
import java.util.Optional;

public interface GetTodoUseCase {
  Optional<Todo> getTodoById(Long id);
  List<Todo> getAllTodos();
  List<Todo> getTodosByStatus(boolean completed);
}