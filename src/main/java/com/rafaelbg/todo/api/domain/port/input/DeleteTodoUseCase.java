package com.rafaelbg.todo.api.domain.port.input;

public interface DeleteTodoUseCase {
  boolean deleteTodo(Long id);
}