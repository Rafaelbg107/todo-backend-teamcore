package com.rafaelbg.todo.api.domain.port.input;

import com.rafaelbg.todo.api.domain.model.Todo;
import java.time.LocalDateTime;
import java.util.Optional;

public interface UpdateTodoUseCase {
  Optional<Todo> updateTodo(Long id, String title, String description, LocalDateTime dueDate);
  Optional<Todo> markTodoAsCompleted(Long id);
  Optional<Todo> markTodoAsPending(Long id);
}