package com.enviro.assessment.grad001.kamogelotsatsi.repository;

import com.enviro.assessment.grad001.kamogelotsatsi.model.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long> {
    @Query("SELECT w FROM WasteCategory w WHERE UPPER(w.category) = UPPER(?1)")
    Optional<WasteCategory> findByCategory(String category);
}
