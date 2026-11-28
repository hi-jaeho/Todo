package com.jaeho.todo.repository;

import com.jaeho.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByTargetDate(LocalDate targetDate);
    List<Todo> findByTargetDateBetween(LocalDate startDay, LocalDate endDay);
}
