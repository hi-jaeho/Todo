package com.jaeho.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TodoUpdateRequest {
    @NotBlank
    @Size(max = 40)
    private String title;

    @Size(max = 2000)
    private String detail;

    @NotNull
    private LocalDate targetDate;
}
