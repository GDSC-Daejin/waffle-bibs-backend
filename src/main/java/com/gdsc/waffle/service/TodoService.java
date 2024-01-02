package com.gdsc.waffle.service;

import com.gdsc.waffle.dto.TodoResponseDto;
import com.gdsc.waffle.dto.TodoRequestDto;
import com.gdsc.waffle.entity.TodoEntity;

import java.util.List;

public interface TodoService {
    void addTodo(Long categoryId, TodoRequestDto todoRequestDto);
    void deleteTodo(Long id);
    List<TodoResponseDto> findAll(Long id);
    TodoResponseDto findById(Long id);
    void update(Long id, TodoRequestDto updateParam);
    boolean existsId(Long categoryId);


    // request
    // dto --> Entity 로 변환
    default TodoEntity reqToEntity(TodoRequestDto todoRequestDto) {
        TodoEntity todoEntity = TodoEntity.builder()
                .contents(todoRequestDto.getContents())
                .complete_chk(todoRequestDto.getComplete_chk())
                .build();
        return todoEntity;
    }

    // Entity --> dto 로 변환
    default TodoRequestDto reqToDto(TodoEntity todoEntity) {
        TodoRequestDto todoRequestDto = TodoRequestDto.builder()
                .contents(todoEntity.getContents())
                .complete_chk(todoEntity.getComplete_chk())
                .build();
        return todoRequestDto;
    }


    // response
    // dto --> Entity 로 변환
    default TodoEntity resToEntity(TodoResponseDto todoResponseDto) {
        TodoEntity todoEntity = TodoEntity.builder()
                .contents(todoResponseDto.getContents())
                .complete_chk(todoResponseDto.getComplete_chk())
                .build();
        return todoEntity;
    }

    // Entity --> dto 로 변환
    default TodoResponseDto resToDto(TodoEntity todoEntity) {
        TodoResponseDto todoResponseDto = TodoResponseDto.builder()
                .id(todoEntity.getId())
                .contents(todoEntity.getContents())
                .complete_chk(todoEntity.getComplete_chk())
                .categoryTitle(todoEntity.getCategory().getTitle())
                .build();
        return todoResponseDto;
    }
}
