package com.enviro.assessment.grad001.kamogelotsatsi.repository;

import com.enviro.assessment.grad001.kamogelotsatsi.model.DisposalGuideline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DisposalGuidelineRepository extends JpaRepository<DisposalGuideline, Long> {

    @Query("SELECT d FROM DisposalGuideline d WHERE UPPER(d.wasteCategory) = UPPER(?1)")
    List<DisposalGuideline> findAllByCategory(String category);
}
