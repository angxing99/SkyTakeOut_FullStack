package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetMealMapper setMealMapper;


    /**
     * Category query by page
     *
     * @param categoryPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {

        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());

        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        List<Category> records = page.getResult();

        return new PageResult(page.getTotal(), records);

    }

    /**
     * Start or stop category
     *
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {

        Category category = Category.builder()
                .id(id)
                .status(status)
                .build();

        categoryMapper.update(category);

    }

    /**
     * Add New Category
     *
     * @param categoryDTO
     */
    public void addNewCategory(CategoryDTO categoryDTO) {

        Category category = new Category();

        BeanUtils.copyProperties(categoryDTO, category);


        category.setStatus(StatusConstant.DISABLE);


        categoryMapper.insert(category);

    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO) {

        Category category = new Category();

        BeanUtils.copyProperties(categoryDTO, category);

        categoryMapper.update(category);


    }

    /**
     * Delete category
     *
     * @param id
     */
    @Override
    public void deleteCategory(Long id) {

        int countDish = dishMapper.countByCategoryId(id);

        if (countDish > 0) {
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }

        int countSetMeal = setMealMapper.countByCategoryId(id);

        if (countSetMeal > 0) {
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);

        }

        categoryMapper.delete(id);

    }

    /**
     * List category by type
     *
     * @param type
     * @return
     */
    public List<Category> listCategoryByType(Integer type) {
        return categoryMapper.list(type);
    }
}
