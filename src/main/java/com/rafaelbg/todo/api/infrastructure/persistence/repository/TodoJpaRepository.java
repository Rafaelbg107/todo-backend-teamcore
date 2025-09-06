package com.rafaelbg.todo.api.infrastructure.persistence.repository;

import com.rafaelbg.todo.api.infrastructure.persistence.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoJpaRepository extends JpaRepository<TodoEntity, Long> {
    
    List<TodoEntity> findByCompleted(boolean completed);
}