package com.enviro.assessment.grad001.kamogelotsatsi.service;

import com.enviro.assessment.grad001.kamogelotsatsi.exceptions.InvalidEntryException;
import com.enviro.assessment.grad001.kamogelotsatsi.exceptions.ValueAlreadyExistsException;
import com.enviro.assessment.grad001.kamogelotsatsi.model.RecyclingTip;
import com.enviro.assessment.grad001.kamogelotsatsi.repository.RecylingTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecyclingTipService {

    private final RecylingTipRepository tipRepository;

    @Autowired
    public RecyclingTipService(RecylingTipRepository tipRepository) {
        this.tipRepository = tipRepository;
    }


    public List<RecyclingTip> getRecyclingTips() {
        return tipRepository.findAll();
    }

    public RecyclingTip getRecyclingTipById(Long tipId) {
        Optional<RecyclingTip> recyclingTip = tipRepository.findById(tipId);
        return recyclingTip.orElseThrow(() -> new InvalidEntryException(
                "Tip with id: "+tipId+" does not exist"));
    }

    public void addNewTip(RecyclingTip recyclingTip) {
        Optional<RecyclingTip> tipOptional = tipRepository.findByTip(
                recyclingTip.getTip());

        if (tipOptional.isPresent()) {
            throw new ValueAlreadyExistsException("Recycling Tip already exists");
        }

        if (recyclingTip.getTip().isEmpty()) {
            throw new InvalidEntryException("Invalid Request: Recycling Tip cannot be blank");
        }
        tipRepository.save(recyclingTip);
    }

    public void deleteTip(Long tipId) {
        boolean exists = tipRepository.existsById(tipId);
        if (!exists) {
            throw new InvalidEntryException(
                "Recycling tip with id: "+tipId+" does not exist");
        }
        tipRepository.deleteById(tipId);
    }

    public RecyclingTip updateTip(Long tipId, RecyclingTip recyclingTip) {
        RecyclingTip existingTip = tipRepository.findById(tipId)
                .orElseThrow(() -> new InvalidEntryException(
                        "Tip with id: " + tipId + " does not exist"));

        if (recyclingTip.getTip() != null && !recyclingTip.getTip().isEmpty()) {
            existingTip.setTip(recyclingTip.getTip());
        }
        return tipRepository.save(existingTip);
    }
}
