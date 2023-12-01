package com.gdsc.waffle.controller;

import com.gdsc.waffle.dto.TodoDto;
import com.gdsc.waffle.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
@Api(tags = "Todo 컨트롤러")
public class TodoController {

    private final TodoService todoService;

    @ApiOperation(value = "todo 목록 전체 조회 API", notes = "카테고리 안에 들어있는 todo 목록 전체를 조회하는 API 입니다.")
    @GetMapping("/{categoryId}/todo")
    public ResponseEntity<List<TodoDto>> getAllTodos(@PathVariable Long categoryId) {
        List<TodoDto> todos = todoService.findAll(categoryId);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @ApiOperation(value = "todo 상세 조회 API", notes = "선택한 Todo의 자세한 정보를 조회하는 API 입니다.")
    @ApiResponses({
                    @ApiResponse(responseCode = "200", description = "성공적으로 조회되었습니다."),
                    @ApiResponse(responseCode = "404", description = "해당 Todo를 찾을 수 없습니다.")
    })
    @GetMapping("/todo/{todoId}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long todoId) {

        TodoDto todo = todoService.findById(todoId);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @ApiOperation(value = "todo 생성 API", notes = "새로운 Todo를 생성하는 API 입니다.")
    @PostMapping("/{categoryId}/todo/add")
    public ResponseEntity<TodoDto> addTodo(@PathVariable Long categoryId, @RequestBody TodoDto todoDto) {
        todoService.addTodo(categoryId, todoDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "todo 수정 API", notes = "선택한 Todo의 내용을 수정하는 API 입니다.")
    @PatchMapping("/todo/{todoId}/update")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long todoId, @RequestBody TodoDto todoDto) {
        todoService.update(todoId, todoDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "todo 삭제 API", notes = "선택한 Todo를 삭제하는 API 입니다.")
    @DeleteMapping("/todo/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}