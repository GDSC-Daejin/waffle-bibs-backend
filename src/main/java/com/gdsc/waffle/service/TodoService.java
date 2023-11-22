package com.gdsc.waffle.service;

import com.gdsc.waffle.dto.TodoDto;
import com.gdsc.waffle.entity.TodoEntity;

public interface TodoService {
    void addTodo(Long categoryId, TodoDto todoDto);
    void deleteTodo(Long id);
    TodoDto findById(Long id);
    void update(Long id, TodoDto updateParam);

    // dto --> Entity 로 변환
    default TodoEntity dtoToEntity(TodoDto todoDto) {
        TodoEntity todoEntity = TodoEntity.builder()
                .id(todoDto.getId())
                .title(todoDto.getTitle())
                .contents(todoDto.getContents())
                .complete_chk(todoDto.getComplete_chk())
                .startTime(todoDto.getStartTime())
                .category(todoDto.getCategory())
                .build();
        return todoEntity;
    }

    // Entity --> dto 로 변환
    default TodoDto entityToDto(TodoEntity todoEntity) {
        TodoDto todoDto = TodoDto.builder()
                .id(todoEntity.getId())
                .title(todoEntity.getTitle())
                .contents(todoEntity.getContents())
                .complete_chk(todoEntity.getComplete_chk())
                .startTime(todoEntity.getStartTime())
                .category(todoEntity.getCategory())
                .build();
        return todoDto;
    }
}
