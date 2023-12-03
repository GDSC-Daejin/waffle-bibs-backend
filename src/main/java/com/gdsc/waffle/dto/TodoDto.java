package com.gdsc.waffle.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private Long id;
    private String title;
    private String contents;
    private Boolean complete_chk;
    private LocalDate startTime;
    private String categoryTitle;
}