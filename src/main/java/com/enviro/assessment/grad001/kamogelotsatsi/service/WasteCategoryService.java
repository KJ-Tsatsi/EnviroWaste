package com.enviro.assessment.grad001.kamogelotsatsi.service;

import com.enviro.assessment.grad001.kamogelotsatsi.exceptions.ValueAlreadyExistsException;
import com.enviro.assessment.grad001.kamogelotsatsi.exceptions.InvalidEntryException;
import com.enviro.assessment.grad001.kamogelotsatsi.model.WasteCategory;
import com.enviro.assessment.grad001.kamogelotsatsi.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return category.orElseThrow(() -> new InvalidEntryException(
                "Category with id: "+categoryId+" does not exist"));
    }


    public void addNewCategory(WasteCategory wasteCategory) {
        Optional<WasteCategory> wasteCategoryOptional = wasteCategoryRepository
                .findByCategory(wasteCategory.getCategory());

        if (wasteCategoryOptional.isPresent()) {
            throw new ValueAlreadyExistsException("Category already exists");
        }

        if (wasteCategory.getCategory().isEmpty()) {
            throw new InvalidEntryException("Invalid request: Category cannot be blank/empty!");
        }
        wasteCategoryRepository.save(wasteCategory);
    }

    public void deleteCategory(Long categoryId) {
        boolean exists = wasteCategoryRepository.existsById(categoryId);
        if (!exists) {
            throw new InvalidEntryException(
                    "Category with id: " +categoryId+ " does not exist");
        }
        wasteCategoryRepository.deleteById(categoryId);
    }


    public WasteCategory updateCategory(Long categoryId, WasteCategory category) {
        WasteCategory wasteCategory = wasteCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new InvalidEntryException(
                        "Category with id: " +categoryId+ " does not exist"));

        if (category.getCategory() != null && !category.getCategory().isEmpty()) {
            wasteCategory.setCategory(category.getCategory());
        }
        return wasteCategoryRepository.save(wasteCategory);
    }
}
