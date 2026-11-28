package com.jaeho.todo.controller;

import com.jaeho.todo.domain.Todo;
import com.jaeho.todo.dto.FeedbackUpdateRequest;
import com.jaeho.todo.dto.TodoCreateRequest;
import com.jaeho.todo.dto.TodoUpdateRequest;
import com.jaeho.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController // RestController -> JSON을 반환하는 REST API에 사용
public class TodoController {
    private final TodoService todoService;

    // Todo 등록
    @PostMapping("/todo/new")
    // @Valid는 DTO에서 @NotBlank, @NotNull, @Size 때문에 쓴 거
    public Todo createTodo(@Valid @RequestBody TodoCreateRequest todoCreateRequest) {
        return todoService.createTodo(todoCreateRequest);
    }

    // Todo 전체 조회
    @GetMapping("/get/todo/all")
    public List<Todo> findAll() {
        return todoService.findAll();
    }

    // Todo 수정
    @PutMapping("/update/todo/{id}")
    public Todo updateTodo(@PathVariable Long id,
                           @Valid @RequestBody TodoUpdateRequest todoUpdateRequest) {
        return todoService.update(id, todoUpdateRequest);
    }

    // Todo 삭제
    @DeleteMapping("/delete/todo/{id}")
    public Todo deleteTodo(@PathVariable Long id) {
        return todoService.delete(id);
    }

    // Todo 일별 조회
    @GetMapping("/get/daily/{date}")
    public List<Todo> findByDate(@PathVariable String date) {
        LocalDate targetDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return todoService.findByDate(targetDate);
    }

    // 완료 토글 기능
    @PutMapping("/update/todo/complete/{id}")
    public Todo completeTodo(@PathVariable Long id) {
        return todoService.completeTodo(id);
    }

    // 월별 조회
    @GetMapping("/get/todo/monthly/{targetMonth}")
    public List<Todo> findTodoMonth(@PathVariable String targetMonth) {
        // Yearmonth = 'yyyy-MM'
        YearMonth yearMonth = YearMonth.parse(targetMonth, DateTimeFormatter.ofPattern("yyyy-MM"));

        // 해당 월의 시작
        LocalDate startDay = yearMonth.atDay(1);
        // 해당 월의 끝
        LocalDate endDay = yearMonth.atEndOfMonth();

        return todoService.findByMonth(startDay, endDay);
    }

    // 피드백 등록 및 수정
    @PutMapping("/update/feedback/{id}")
    public Todo updateFeedback(@PathVariable Long id,
                               @Valid @RequestBody FeedbackUpdateRequest feedbackUpdateRequest) {
        return todoService.updateFeedback(id, feedbackUpdateRequest);
    }

}
