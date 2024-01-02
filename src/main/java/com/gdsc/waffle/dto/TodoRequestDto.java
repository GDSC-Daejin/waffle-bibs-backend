package com.gdsc.waffle.dto;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequestDto {
    private String contents;
    private Boolean complete_chk;
}
