package com.enviro.assessment.grad001.kamogelotsatsi.repository;

import com.enviro.assessment.grad001.kamogelotsatsi.model.RecyclingTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecylingTipRepository extends JpaRepository<RecyclingTip, Long> {
    @Query("SELECT r FROM RecyclingTip r WHERE UPPER(r.tip) = UPPER(?1)")
    Optional<RecyclingTip> findByTip(String tip);
}
