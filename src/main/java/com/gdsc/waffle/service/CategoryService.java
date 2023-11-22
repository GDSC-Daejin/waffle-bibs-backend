package com.gdsc.waffle.service;

import com.gdsc.waffle.dto.CategoryDto;
import com.gdsc.waffle.entity.CategoryEntity;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryService extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);



    // Entity --> dto 로 변환
    default Category entityToDto(CategoryEntity categoryEntity) {
        return (Category) CategoryDto.builder()
                .id(categoryEntity.getId())
                .title(categoryEntity.getTitle())
                .build();
    }

    public default List<Object> findAllCategories() {
        JpaRepository<Object, Object> categoryRepository = null;
        return categoryRepository.findAll();
    }

    Optional<CategoryDto> entityToDto(Long id);
}
