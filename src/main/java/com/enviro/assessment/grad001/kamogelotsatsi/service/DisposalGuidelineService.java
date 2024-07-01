package com.enviro.assessment.grad001.kamogelotsatsi.service;

import com.enviro.assessment.grad001.kamogelotsatsi.exceptions.InvalidEntryException;
import com.enviro.assessment.grad001.kamogelotsatsi.model.DisposalGuideline;
import com.enviro.assessment.grad001.kamogelotsatsi.repository.DisposalGuidelineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisposalGuidelineService {

    private final DisposalGuidelineRepository guidelineRepository;

    public DisposalGuidelineService(DisposalGuidelineRepository guidelineRepository) {
        this.guidelineRepository = guidelineRepository;
    }

    public List<DisposalGuideline> getAllGuidelines() {
        return guidelineRepository.findAll();
    }

    public DisposalGuideline getGuidelineById(Long guidelineId) {
        Optional<DisposalGuideline> disposalGuideline = guidelineRepository.findById(guidelineId);
        return disposalGuideline.orElseThrow(() -> new InvalidEntryException(
                "Disposal Guideline with id: "+guidelineId+" does not exist"));
    }

    public List<DisposalGuideline> getGuidelinesByCategory(String category) {
        List<DisposalGuideline> guidelines = guidelineRepository.findAllByCategory(category);
        if (guidelines.isEmpty()) {
            throw new InvalidEntryException("Category: "+category+" does not exist");
        }
        return guidelines;
    }

    public void addNewGuideline(DisposalGuideline guideline) {
        if (guideline.getGuideline().isEmpty() || guideline.getWasteCategory().isEmpty()) {
            throw new InvalidEntryException("Invalid Request: Guideline or Category cannot be empty");
        }
        guidelineRepository.save(guideline);
    }

    public void deleteGuideline(Long guidelineId) {
        boolean exists = guidelineRepository.existsById(guidelineId);
        if (!exists) {
            throw new InvalidEntryException("Guideline with id: " +guidelineId+ " does not exist");
        }
        guidelineRepository.deleteById(guidelineId);
    }


    public DisposalGuideline updateGuideline(Long guidelineId, DisposalGuideline guideline) {
        DisposalGuideline existingGuideline = guidelineRepository.findById(guidelineId).orElseThrow(() ->
                new InvalidEntryException("Guideline with id: " +guidelineId+ " does not exist"));

        if (guideline.getGuideline() != null && !guideline.getGuideline().isEmpty()) {
            existingGuideline.setGuideline(guideline.getGuideline());
        }

        if (guideline.getWasteCategory() != null && !guideline.getWasteCategory().isEmpty()) {
            existingGuideline.setWasteCategory(guideline.getWasteCategory());
        }
        return guidelineRepository.save(existingGuideline);
    }
}
