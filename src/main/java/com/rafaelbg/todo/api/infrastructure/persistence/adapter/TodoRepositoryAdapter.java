package com.rafaelbg.todo.api.infrastructure.persistence.adapter;

import com.rafaelbg.todo.api.domain.model.Todo;
import com.rafaelbg.todo.api.domain.port.output.TodoRepository;
import com.rafaelbg.todo.api.infrastructure.persistence.entity.TodoEntity;
import com.rafaelbg.todo.api.infrastructure.persistence.repository.TodoJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TodoRepositoryAdapter implements TodoRepository {
    
  private final TodoJpaRepository jpaRepository;
  
  public TodoRepositoryAdapter(TodoJpaRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }
  
  @Override
  public Todo save(Todo todo) {
    TodoEntity entity = toEntity(todo);
    TodoEntity saved = jpaRepository.save(entity);
    return toDomain(saved);
  }
  
  @Override
  public Optional<Todo> findById(Long id) {
    return jpaRepository.findById(id)
            .map(this::toDomain);
  }
  
  @Override
  public List<Todo> findAll() {
    return jpaRepository.findAll()
            .stream()
            .map(this::toDomain)
            .collect(Collectors.toList());
  }
  
  @Override
  public List<Todo> findByCompleted(boolean completed) {
    return jpaRepository.findByCompleted(completed)
            .stream()
            .map(this::toDomain)
            .collect(Collectors.toList());
  }
  
  @Override
  public void deleteById(Long id) {
    jpaRepository.deleteById(id);
  }
  
  @Override
  public boolean existsById(Long id) {
    return jpaRepository.existsById(id);
  }
  
  // Mapping methods
  private TodoEntity toEntity(Todo todo) {
    TodoEntity entity = new TodoEntity(
            todo.getTitle(),
            todo.getDescription(),
            todo.isCompleted(),
            todo.getDueDate(),
            todo.getCreatedAt(),
            todo.getUpdatedAt()
    );
    
    // Preserve ID for updates
    if (todo.getId() != null) {
      entity.setId(todo.getId());
    }
    
    return entity;
  }
  
  private Todo toDomain(TodoEntity entity) {
    Todo todo = new Todo();
    todo.setId(entity.getId());
    todo.setTitle(entity.getTitle());
    todo.setDescription(entity.getDescription());
    todo.setCompleted(entity.isCompleted());
    todo.setDueDate(entity.getDueDate());
    todo.setCreatedAt(entity.getCreatedAt());
    todo.setUpdatedAt(entity.getUpdatedAt());
    return todo;
  }
}