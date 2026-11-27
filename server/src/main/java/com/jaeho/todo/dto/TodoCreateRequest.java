package com.jaeho.todo.dto;

import com.jaeho.todo.domain.Todo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class TodoCreateRequest {

    @NotBlank // 공백만 있는거 금지
    @Size(max = 40)
    private String title;

    @NotNull
    private LocalDate targetDate;

    @Size(max = 2000)
    private String detail;

    public Todo toEntity() {
        return new Todo(title, targetDate, detail);
    }
}
