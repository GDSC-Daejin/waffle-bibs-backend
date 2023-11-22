package com.gdsc.waffle.controller;

import com.gdsc.waffle.service.CategoryService;
import io.swagger.annotations.SwaggerDefinition;
import jdk.jfr.Category;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@SwaggerDefinition
@EnableSwagger2
@RestController
@RequestMapping("/categories")
public class CateController {
    private CategoryService categoryService;

    public void CategController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
   @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
   }


}
