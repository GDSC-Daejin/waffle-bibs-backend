package com.gdsc.waffle.controller;

import com.gdsc.waffle.dto.TodoResponseDto;
import com.gdsc.waffle.dto.TodoRequestDto;
import com.gdsc.waffle.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




import java.util.Collections;
import java.util.Comparator;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
@Api(tags = "Todo 컨트롤러")
public class TodoController {

    private final TodoService todoService;

    @ApiOperation(value = "todo 목록 전체 조회 API", notes = "카테고리 안에 들어있는 todo 목록 전체를 조회하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 조회되었습니다."),
            @ApiResponse(responseCode = "404", description = "해당 카테고리를 찾을 수 없습니다.")
    })
    @GetMapping("/{categoryId}/todo")
    public ResponseEntity<List<TodoResponseDto>> getAllTodos(@PathVariable Long categoryId) {
        if(todoService.existsId(categoryId)) {
            List<TodoResponseDto> todos = todoService.findAll(categoryId);

            // ID를 기준으로 정렬
            Collections.sort(todos, new Comparator<TodoResponseDto>() {
                @Override
                public int compare(TodoResponseDto o1, TodoResponseDto o2) {
                    return (int) (o1.getId() - o2.getId());
                }
            });

            return new ResponseEntity<>(todos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "todo 상세 조회 API", notes = "선택한 Todo의 자세한 정보를 조회하는 API 입니다.")
    @ApiResponses({
                    @ApiResponse(responseCode = "200", description = "성공적으로 생성되었습니다."),
                    @ApiResponse(responseCode = "404", description = "해당 Todo를 찾을 수 없습니다.")
    })
    @GetMapping("/todo/{todoId}")
    public ResponseEntity<TodoResponseDto> getTodo(@PathVariable Long todoId) {

        TodoResponseDto todo = todoService.findById(todoId);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @ApiOperation(value = "todo 생성 API", notes = "새로운 Todo를 생성하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 조회되었습니다."),
            @ApiResponse(responseCode = "404", description = "해당 카테고리를 찾을 수 없습니다.")
    })
    @PostMapping("/{categoryId}/todo/add")
    public ResponseEntity<TodoRequestDto> addTodo(@PathVariable Long categoryId, @RequestBody TodoRequestDto todoRequestDto) {
        if(todoService.existsId(categoryId)) {
            todoService.addTodo(categoryId, todoRequestDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "todo 수정 API", notes = "선택한 Todo의 내용을 수정하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 수정되었습니다.."),
            @ApiResponse(responseCode = "404", description = "해당 Todo를 찾을 수 없습니다.")
    })
    @PatchMapping("/todo/{todoId}/update")
    public ResponseEntity<TodoRequestDto> updateTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto todoRequestDto) {
        todoService.update(todoId, todoRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "todo 삭제 API", notes = "선택한 Todo를 삭제하는 API 입니다.")
    @DeleteMapping("/todo/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}