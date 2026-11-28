package com.jaeho.todo.service;

import com.jaeho.todo.domain.Todo;
import com.jaeho.todo.dto.FeedbackUpdateRequest;
import com.jaeho.todo.dto.TodoCreateRequest;
import com.jaeho.todo.dto.TodoUpdateRequest;
import com.jaeho.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    // 전체 조회
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    // 단일 조회(id로 찾기)
    public Todo findById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일을 찾지 못했습니다."));
    }

    // 수정
    @Transactional // 메서드 안의 DB 작업을 하나의 작업 묶음으로 처리하라
    // -> 지금 findById와 update 2개의 DB 작업 -> 2개 다 성공 시에만 커밋!
    public Todo update(Long id, TodoUpdateRequest todoUpdateRequest) {
        Todo todo = findById(id);

        todo.update(todoUpdateRequest.getTitle(),
            todoUpdateRequest.getTargetDate(),
            todoUpdateRequest.getDetail());

        return todo;
    }

    // 삭제
    public Todo delete(Long id) {
        Todo todo = findById(id);
        todoRepository.deleteById(id);

        return todo;
    }

    // 일별 조회
    public List<Todo> findByDate(LocalDate targetDate) {
        return todoRepository.findByTargetDate(targetDate);
    }

    // 완료 상태 변경
    @Transactional // 메서드가 정상적으로 끝날 때 커밋
    public Todo completeTodo(Long id) {
        Todo todo = findById(id);
        todo.updateComplete();

        return todo;
    }

    // 월별 조회
    public List<Todo> findByMonth(LocalDate startDay, LocalDate endDay) {
        return todoRepository.findByTargetDateBetween(startDay, endDay);
    }

    // 피드백 등록 및 수정
    @Transactional
    public Todo updateFeedback(Long id, FeedbackUpdateRequest feedbackUpdateRequest) {
        Todo todo = findById(id);
        todo.updateFeedback(feedbackUpdateRequest.getFeedback());

        return todo;
    }
}
