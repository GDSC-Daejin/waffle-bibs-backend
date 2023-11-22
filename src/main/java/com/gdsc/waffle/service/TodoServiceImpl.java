package com.gdsc.waffle.service;

import com.gdsc.waffle.dto.TodoDto;
import com.gdsc.waffle.entity.CategoryEntity;
import com.gdsc.waffle.entity.TodoEntity;
import com.gdsc.waffle.repository.CategoryRepository;
import com.gdsc.waffle.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

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
    public List<TodoDto> findAll() {
        ArrayList<TodoDto> todoDtolist = new ArrayList<>();
        List<TodoEntity> todoEntities = todoRepository.findAll();
        for (TodoEntity todoEntity : todoEntities)  todoDtolist.add(entityToDto(todoEntity));
        return todoDtolist;
    }

    @Override
    public TodoDto findById(Long id) {
        TodoEntity todoEntity = todoRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 Todo가 존재하지 않습니다."));
        return entityToDto(todoEntity);
    }

    @Override
    public void update(Long id, TodoDto updateParam) {
        TodoEntity todoEntity = dtoToEntity(findById(id));
        todoEntity.setTitle(updateParam.getTitle());
        todoEntity.setComplete_chk(updateParam.getComplete_chk());
        todoEntity.setStartTime(updateParam.getStartTime());
        todoEntity.setCategory(updateParam.getCategory());
        todoRepository.save(todoEntity);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        return null;
    }

    @Override
    public TodoDto updateTodo(Long id, TodoDto todoDto) {
        return null;
    }

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        return null;
    }

    @Override
    public TodoDto getTodoById(Long id) {
        return null;
    }
}
