package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminCategoryController")
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    /**
     * Category query by page
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "Category Query By Page")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){

        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);

        return Result.success(pageResult);
    }

    /**
     * Start or stop category
     * @param status
     * @param id
     * @return
     */
    @PostMapping("status/{status}")
    @ApiOperation(value = "Enable or Disable Category")
    public Result startOrStop(@PathVariable Integer status, Long id){

        categoryService.startOrStop(status, id);
        return Result.success();
    }


    /**
     * Add new category
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @ApiOperation(value = "Add New Category")
    public Result addCategory(@RequestBody CategoryDTO categoryDTO){

        categoryService.addNewCategory(categoryDTO);

        return Result.success();
    }

    @PutMapping
    @ApiOperation(value = "Edit  Category")
    public Result editCategory(@RequestBody CategoryDTO categoryDTO){

        categoryService.updateCategory(categoryDTO);

        return Result.success();
    }

    @DeleteMapping()
    public Result deleteCategory(Long id){

        categoryService.deleteCategory(id);

        return Result.success();
    }


    @GetMapping("/list")
    @ApiOperation(value = "List category by type")
    public Result<List<Category>> listCategoryByType(Integer type){

        List<Category> list = categoryService.listCategoryByType(type);

        return Result.success(list);
    }


}
