package com.jaeho.todo.controller;

import com.jaeho.todo.domain.Todo;
import com.jaeho.todo.dto.TodoCreateRequest;
import com.jaeho.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController // RestController -> JSON을 반환하는 REST API에 사용
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/post/new")
    // @Valid는 DTO에서 @NotBlank, @NotNull, @Size 때문에 쓴 거
    public Todo createTodo(@Valid @RequestBody TodoCreateRequest todoCreateRequest) {
        return todoService.createTodo(todoCreateRequest);
    }
}
