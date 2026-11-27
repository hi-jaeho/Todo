package com.jaeho.todo.service;

import com.jaeho.todo.domain.Todo;
import com.jaeho.todo.dto.TodoCreateRequest;
import com.jaeho.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // final인 필드 자동 생성해줌
@Service // 서비스 계층임을 알려주기
public class TodoService {
    private final TodoRepository todoRepository;

    // 들어온 request를 받고, 저장된 Todo 객체 반환
    public Todo createTodo(TodoCreateRequest todoCreateRequest) {
        Todo todo = todoCreateRequest.toEntity();

        // save()가 저장된 엔티티 바로 반환함
        return todoRepository.save(todo);
    }
}
