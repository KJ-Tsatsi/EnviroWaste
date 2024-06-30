package com.enviro.assessment.grad001.kamogelotsatsi.service;

import com.enviro.assessment.grad001.kamogelotsatsi.model.WasteCategory;
import com.enviro.assessment.grad001.kamogelotsatsi.repository.WasteCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WasteCategoryService {

    private final WasteCategoryRepository wasteCategoryRepository;

    @Autowired
    public WasteCategoryService(WasteCategoryRepository wasteCategoryRepository) {
        this.wasteCategoryRepository = wasteCategoryRepository;
    }

    public List<WasteCategory> getWasteCategories() {
        return wasteCategoryRepository.findAll();
    }

    public WasteCategory getWasteCategoryById(Long categoryId) {
        Optional<WasteCategory> category = wasteCategoryRepository.findById(categoryId);
        return category.orElseThrow(() -> new IllegalStateException(
                "Category with id: "+categoryId+" does not exist"));
    }


    public void addNewCategory(WasteCategory wasteCategory) {
        Optional<WasteCategory> wasteCategoryOptional = wasteCategoryRepository
                .findByCategory(wasteCategory.getCategory());

        if (wasteCategoryOptional.isPresent()) {
            throw new IllegalStateException("Category already exists");
        }
        wasteCategoryRepository.save(wasteCategory);
    }

    public void deleteCategory(Long categoryId) {
        boolean exists = wasteCategoryRepository.existsById(categoryId);
        if (!exists) {
            throw new IllegalStateException(
                    "Category with id: " +categoryId+ " does not exist");
        }
        wasteCategoryRepository.deleteById(categoryId);
    }


    public WasteCategory updateCategory(Long categoryId, WasteCategory category) {
        WasteCategory wasteCategory = wasteCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException(
                        "Category with id: " +categoryId+ " does not exist"));

        if (category.getCategory() != null && !category.getCategory().isEmpty()) {
            wasteCategory.setCategory(category.getCategory());
        }
        return wasteCategoryRepository.save(wasteCategory);
    }
}
