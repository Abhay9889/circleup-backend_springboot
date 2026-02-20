package com.CircleUp.CircleUp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Getter
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String authorName;
}
