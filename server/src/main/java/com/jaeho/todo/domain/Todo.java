package com.jaeho.todo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 파라미터가 없는 디폴트 생성자를 자동으로 생성
// @Setter - JPA는 필드에 직접 값 넣을 수 있어서 Setter 필요 X
@Getter
@Entity // 데이터베이스 테이블과 연결되는 객체로 사용
public class Todo {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment로 생성
    @Id // PK
    private Long id; // id

    @Column(nullable = false, length = 40)
    private String title; // title

    @Column(nullable = false)
    private LocalDate targetDate; // 수행 예정 날짜(yyyy-MM-dd)

    @Column(length = 2000)
    private String detail; // todo-detail

    private boolean completed = false; // checker

    @Column(length = 2000)
    private String feedback; // feedback

    // 기본 생성자 protected -> 생성자가 필요 (new Todo(...) 이렇게 정의하기 힘듦)
    public Todo(String title, LocalDate targetDate, String detail) {
        this.title = title;
        this.targetDate = targetDate;
        this.detail = detail;
        this.completed = false;
    }
}
