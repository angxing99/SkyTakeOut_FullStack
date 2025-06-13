package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {

    /**
     * Category query by page
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * Start Or Stop category
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * Add New Category
     * @param categoryDTO
     */
    void addNewCategory(CategoryDTO categoryDTO);

    /**
     * Edit category
     * @param categoryDTO
     */
    void updateCategory(CategoryDTO categoryDTO);

    /**
     * Delete category
     * @param id
     */
    void deleteCategory(Long id);

    /**
     * List Category by type
     * @param type
     * @return
     */
    List<Category> listCategoryByType(Integer type);
}
