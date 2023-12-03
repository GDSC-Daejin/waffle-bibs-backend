package com.gdsc.waffle.service;

import com.gdsc.waffle.dto.CategoryDto;
import com.gdsc.waffle.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    CategoryDto findById(Long id);
    List<CategoryDto> findAll();

    // Entity --> dto 로 변환
    default CategoryDto entityToDto(CategoryEntity categoryEntity) {
        CategoryDto categoryDto = CategoryDto.builder()
                .title(categoryEntity.getTitle())
                .build();
        return categoryDto;
    }
}
