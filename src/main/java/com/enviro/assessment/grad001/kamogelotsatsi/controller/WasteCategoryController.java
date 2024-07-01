package com.enviro.assessment.grad001.kamogelotsatsi.controller;

import com.enviro.assessment.grad001.kamogelotsatsi.model.WasteCategory;
import com.enviro.assessment.grad001.kamogelotsatsi.service.WasteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/waste")
public class WasteCategoryController {

    private final WasteCategoryService wasteCategoryService;

    @Autowired
    public WasteCategoryController(WasteCategoryService wasteCategoryService) {
        this.wasteCategoryService = wasteCategoryService;
    }

    @GetMapping
    public List<WasteCategory> getWasteCategories() {
        return wasteCategoryService.getWasteCategories();
    }

    @GetMapping(path = "{categoryId}")
    public ResponseEntity<WasteCategory> getWasteCategoryById(
            @PathVariable("categoryId") Long categoryId) {
        WasteCategory wasteCategory = wasteCategoryService.getWasteCategoryById(categoryId);
        return ResponseEntity.ok(wasteCategory);
    }

    @PostMapping
    public void addNewCategory(@RequestBody WasteCategory wasteCategory) {
        wasteCategoryService.addNewCategory(wasteCategory);
    }

    @DeleteMapping(path = "{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
        wasteCategoryService.deleteCategory(categoryId);
    }

    @PutMapping(path = "{categoryId}")
    public ResponseEntity<WasteCategory> updateCategory(
            @PathVariable("categoryId") Long categoryId,
            @RequestBody WasteCategory category) {
        WasteCategory wasteCategory = wasteCategoryService.updateCategory(categoryId, category);
        return ResponseEntity.ok(wasteCategory);
    }


}
