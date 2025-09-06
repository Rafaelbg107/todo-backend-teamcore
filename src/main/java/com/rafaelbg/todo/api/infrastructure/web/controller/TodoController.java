package com.rafaelbg.todo.api.infrastructure.web.controller;

import com.rafaelbg.todo.api.domain.model.Todo;
import com.rafaelbg.todo.api.domain.port.input.*;
import com.rafaelbg.todo.api.infrastructure.web.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class TodoController {
    
  private final CreateTodoUseCase createTodoUseCase;
  private final GetTodoUseCase getTodoUseCase;
  private final UpdateTodoUseCase updateTodoUseCase;
  private final DeleteTodoUseCase deleteTodoUseCase;
  
  public TodoController(CreateTodoUseCase createTodoUseCase,
                      GetTodoUseCase getTodoUseCase,
                      UpdateTodoUseCase updateTodoUseCase,
                      DeleteTodoUseCase deleteTodoUseCase) {
    this.createTodoUseCase = createTodoUseCase;
    this.getTodoUseCase = getTodoUseCase;
    this.updateTodoUseCase = updateTodoUseCase;
    this.deleteTodoUseCase = deleteTodoUseCase;
  }
  
  // Create a new todo
  @PostMapping
  public ResponseEntity<TodoResponse> createTodo(@Valid @RequestBody CreateTodoRequest request) {
    Todo todo = createTodoUseCase.createTodo(
      request.getTitle(),
      request.getDescription(),
      request.getDueDate()
    );
    
    TodoResponse response = toResponse(todo);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
  
  // Get all todos
  @GetMapping
  public ResponseEntity<List<TodoResponse>> getAllTodos() {
    List<Todo> todos = getTodoUseCase.getAllTodos();
    List<TodoResponse> responses = todos.stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
    
    return ResponseEntity.ok(responses);
  }
  
  // Get todos by status
  @GetMapping(params = "completed")
  public ResponseEntity<List<TodoResponse>> getTodosByStatus(@RequestParam boolean completed) {
    List<Todo> todos = getTodoUseCase.getTodosByStatus(completed);
    List<TodoResponse> responses = todos.stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
    
    return ResponseEntity.ok(responses);
  }
  
  // Get todo by ID
  @GetMapping("/{id}")
  public ResponseEntity<TodoResponse> getTodoById(@PathVariable Long id) {
    return getTodoUseCase.getTodoById(id)
        .map(todo -> ResponseEntity.ok(toResponse(todo)))
        .orElse(ResponseEntity.notFound().build());
  }
  
  // Update todo
  @PutMapping("/{id}")
  public ResponseEntity<TodoResponse> updateTodo(@PathVariable Long id, 
                                                @Valid @RequestBody UpdateTodoRequest request) {
    return updateTodoUseCase.updateTodo(
      id,
      request.getTitle(),
      request.getDescription(),
      request.getDueDate()
    ).map(todo -> ResponseEntity.ok(toResponse(todo)))
      .orElse(ResponseEntity.notFound().build());
  }
  
  // Mark todo as completed
  @PatchMapping("/{id}/complete")
  public ResponseEntity<TodoResponse> markTodoAsCompleted(@PathVariable Long id) {
    return updateTodoUseCase.markTodoAsCompleted(id)
        .map(todo -> ResponseEntity.ok(toResponse(todo)))
        .orElse(ResponseEntity.notFound().build());
  }
  
  // Mark todo as pending
  @PatchMapping("/{id}/pending")
  public ResponseEntity<TodoResponse> markTodoAsPending(@PathVariable Long id) {
    return updateTodoUseCase.markTodoAsPending(id)
        .map(todo -> ResponseEntity.ok(toResponse(todo)))
        .orElse(ResponseEntity.notFound().build());
  }
  
  // Delete todo
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
    boolean deleted = deleteTodoUseCase.deleteTodo(id);
    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
  
  // Helper method to convert domain model to response DTO
  private TodoResponse toResponse(Todo todo) {
    return new TodoResponse(
      todo.getId(),
      todo.getTitle(),
      todo.getDescription(),
      todo.isCompleted(),
      todo.getDueDate(),
      todo.getCreatedAt(),
      todo.getUpdatedAt()
    );
  }
}