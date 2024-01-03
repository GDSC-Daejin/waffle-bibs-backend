package com.gdsc.waffle.service;

import com.gdsc.waffle.dto.TodoResponseDto;
import com.gdsc.waffle.dto.TodoRequestDto;
import com.gdsc.waffle.entity.CategoryEntity;
import com.gdsc.waffle.entity.TodoEntity;
import com.gdsc.waffle.repository.CategoryRepository;
import com.gdsc.waffle.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public TodoRequestDto addTodo(Long categoryId, TodoRequestDto todoRequestDto) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(() ->
                new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
        TodoEntity todoEntity = reqToEntity(todoRequestDto);
        todoEntity.setCategory(categoryEntity);
        todoRepository.save(todoEntity);
        return reqToDto(todoEntity);
    }

    @Override
    public TodoResponseDto deleteTodo(Long id) {
        TodoEntity todoEntity = todoRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 Todo를 찾을 수 없습니다."));
        todoRepository.deleteById(id);
        return resToDto(todoEntity);
    }

    @Override
    public List<TodoResponseDto> findAll(Long categoryId) {
        List<TodoEntity> todoEntityList = todoRepository.findByCategoryId(categoryId);
        List<TodoResponseDto> todoDtoList = new ArrayList<>();

        for (TodoEntity todoEntity : todoEntityList)    todoDtoList.add(resToDto(todoEntity));

        return todoDtoList;
    }

    @Override
    public TodoResponseDto findById(Long id) {
        TodoEntity todoEntity = todoRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 Todo를 찾을 수 없습니다."));
        return resToDto(todoEntity);
    }

    @Override
    public TodoRequestDto update(Long id, TodoRequestDto updateParam) {
        TodoEntity todoEntity = todoRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 Todo를 찾을 수 없습니다."));
        todoEntity.setContents(updateParam.getContents());
        todoEntity.setComplete_chk(updateParam.getComplete_chk());
        todoRepository.save(todoEntity);
        return reqToDto(todoEntity);
    }

    @Override
    public boolean existsId(Long categoryId) {
        return categoryRepository.findById(categoryId).isPresent();
    }
}
