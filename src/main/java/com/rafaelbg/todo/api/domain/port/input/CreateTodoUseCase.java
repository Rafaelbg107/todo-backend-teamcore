package com.rafaelbg.todo.api.domain.port.input;

import com.rafaelbg.todo.api.domain.model.Todo;

public interface CreateTodoUseCase {
  Todo createTodo(String title, String description, java.time.LocalDateTime dueDate);
}