package com.jaeho.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FeedbackUpdateRequest {

    @Size(max = 2000)
    private String feedback;
}
