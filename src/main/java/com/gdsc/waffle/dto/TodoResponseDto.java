package com.gdsc.waffle.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponseDto {
    private Long id;
    private String contents;
    private Boolean complete_chk;
    private String categoryTitle;
}