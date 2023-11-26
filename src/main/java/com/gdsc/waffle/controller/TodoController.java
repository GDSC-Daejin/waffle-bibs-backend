package com.gdsc.waffle.controller;

import com.gdsc.waffle.dto.TodoDto;
import com.gdsc.waffle.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation; // swagger 제공 23-11-26

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class TodoController {

    private final TodoService todoService;

    // todo 목록 전체 가져오기
    @ApiOperation(value = "todo 목록 전체 가지오기 api", notes = "todo리스트 목록을 전체 다 가져온다.")
    @GetMapping("/{categoryId}")
    public ResponseEntity<List<TodoDto>> getAllTodos(@PathVariable Long categoryId) {
        List<TodoDto> todos = todoService.findAll();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    // todo 상세조회
    @GetMapping("/{todoId}")
    @ApiOperation(value = "todo 상세조회 api", notes = "todo리스트를 조회한다.")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long todoId) {
        TodoDto todo = todoService.findById(todoId);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    // todo 생성
    @PostMapping("/{categoryId}/add")
    @ApiOperation(value = "todo 생성 api", notes = "todo리스트 생성시 저장된다.")
    public ResponseEntity<TodoDto> addTodo(@PathVariable Long categoryId, @RequestBody TodoDto todoDto) {
        todoService.addTodo(categoryId, todoDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // todo 수정
    @PatchMapping("/{todoId}/update")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long todoId, @ModelAttribute TodoDto todoDto) {
        todoService.update(todoId, todoDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // todo 삭제
    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}