package com.enviro.assessment.grad001.kamogelotsatsi.controller;

import com.enviro.assessment.grad001.kamogelotsatsi.model.DisposalGuideline;
import com.enviro.assessment.grad001.kamogelotsatsi.service.DisposalGuidelineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/waste/guidelines")
public class DisposalGuidelineController {

    private final DisposalGuidelineService guidelineService;


    @Autowired
    public DisposalGuidelineController(DisposalGuidelineService guidelineService) {
        this.guidelineService = guidelineService;
    }

    @GetMapping
    public List<DisposalGuideline> getAllGuidelines() {
        return guidelineService.getAllGuidelines();
    }

    @GetMapping(path = "/id/{guidelineId}")
    public ResponseEntity<DisposalGuideline> getGuidelineById(
            @PathVariable("guidelineId") Long guidelineId) {
        DisposalGuideline guideline = guidelineService.getGuidelineById(guidelineId);
        return ResponseEntity.ok(guideline);
    }

    @GetMapping(path = "/category/{category}")
    public List<DisposalGuideline> getGuidelineByCategory(
            @PathVariable("category") String category) {
        return guidelineService.getGuidelinesByCategory(category);
    }

    @PostMapping
    public ResponseEntity<?> addNewGuideline(@Valid @RequestBody DisposalGuideline guideline, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        guidelineService.addNewGuideline(guideline);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{guidelineId}")
    public void deleteGuideline(@PathVariable("guidelineId") Long guidelineId) {
        guidelineService.deleteGuideline(guidelineId);
    }

    @DeleteMapping(path = "/category/{category}")
    public void deleteGuidelineByCategory(@PathVariable("category") String category) {
        guidelineService.deleteGuidelineByCategory(category);
    }

    @PutMapping(path = "{guidelineId}")
    public ResponseEntity<DisposalGuideline> updateGuideline(
            @PathVariable("guidelineId") Long guidelineId,
            @RequestBody DisposalGuideline disposalGuideline) {
        DisposalGuideline updatedGuideline = guidelineService.updateGuideline(guidelineId, disposalGuideline);
        return ResponseEntity.ok(updatedGuideline);
    }
}
