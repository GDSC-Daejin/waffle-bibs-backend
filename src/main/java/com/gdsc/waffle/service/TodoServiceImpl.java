package com.gdsc.waffle.service;

import com.gdsc.waffle.dto.TodoDto;
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
    public void addTodo(Long categoryId, TodoDto todoDto) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(() ->
                new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
        TodoEntity todoEntity = dtoToEntity(todoDto);
        todoEntity.setCategory(categoryEntity);
        todoRepository.save(todoEntity);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public List<TodoDto> findAll(Long categoryId) {
        List<TodoEntity> todoEntityList = todoRepository.findByCategoryId(categoryId);
        List<TodoDto> todoDtoList = new ArrayList<>();

        for (TodoEntity todoEntity : todoEntityList)    todoDtoList.add(entityToDto(todoEntity));

        return todoDtoList;
    }

    @Override
    public TodoDto findById(Long id) {
        TodoEntity todoEntity = todoRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 Todo를 찾을 수 없습니다."));
        return entityToDto(todoEntity);
    }

    @Override
    public void update(Long id, TodoDto updateParam) {
        TodoEntity todoEntity = dtoToEntity(findById(id));
        todoEntity.setTitle(updateParam.getTitle());
        todoEntity.setContents(updateParam.getContents());
        todoEntity.setComplete_chk(updateParam.getComplete_chk());
        todoEntity.setStartTime(updateParam.getStartTime());
        todoRepository.save(todoEntity);
    }

    @Override
    public boolean existsId(Long categoryId) {
        return categoryRepository.findById(categoryId).isPresent();
    }
}
