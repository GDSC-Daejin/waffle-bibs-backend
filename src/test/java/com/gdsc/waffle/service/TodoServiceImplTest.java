package com.gdsc.waffle.service;
/*
import com.gdsc.waffle.dto.TodoDto;
import com.gdsc.waffle.entity.TodoEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class TodoServiceImplTest {

    @Autowired
    private TodoService todoService;

    @Autowired
    PlatformTransactionManager transactionManager;
    TransactionStatus status;

    @BeforeEach
    void before() {
        status = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    @AfterEach
    void afterEach() {
        // 트랜잭션 롤백
        transactionManager.rollback(status);
    }


    @Test
    @DisplayName("Todo 생성")
    void addTodo() {
        // given
        TodoDto todoDto = new TodoDto();
        todoDto.setTitle("테스트 제목");
        todoDto.setContents("테스트 내용");

        // when
        todoService.addTodo(3L, todoDto);
        List<TodoDto> findTodo = todoService.findAll(3L);

        // then
        assertThat(todoDto.getTitle()).isEqualTo(findTodo.get(0).getTitle());
    }


    @Test
    @DisplayName("Todo 조회")
    void findById() {

        // given
        TodoDto todoDto1 = new TodoDto();
        todoDto1.setContents("제목1");
        TodoDto todoDto2 = new TodoDto();
        todoDto2.setContents("제목2");

        // when
        todoService.addTodo(2L, todoDto1);
        todoService.addTodo(3L, todoDto2);

        // then
        TodoEntity findEntity = todoService.dtoToEntity(todoDto1);
        TodoDto findDto = todoService.entityToDto(findEntity);

        assertThat(findEntity.getId()).isEqualTo(findDto.getId());

    }
}
     */