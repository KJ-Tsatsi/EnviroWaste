package com.enviro.assessment.grad001.kamogelotsatsi.controller;

import com.enviro.assessment.grad001.kamogelotsatsi.model.WasteCategory;
import com.enviro.assessment.grad001.kamogelotsatsi.service.WasteCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> addNewCategory(@Valid @RequestBody WasteCategory wasteCategory, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        wasteCategoryService.addNewCategory(wasteCategory);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
        wasteCategoryService.deleteCategory(categoryId);
    }

    @PutMapping(path = "{categoryId}")
    public ResponseEntity<?> updateCategory(
            @PathVariable("categoryId") Long categoryId,
            @Valid @RequestBody WasteCategory category,
            BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        WasteCategory updatedCategory = wasteCategoryService.updateCategory(categoryId, category);
        return ResponseEntity.ok(updatedCategory);
    }
}
