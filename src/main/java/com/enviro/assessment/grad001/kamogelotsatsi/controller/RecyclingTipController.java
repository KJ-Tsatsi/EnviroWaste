package com.enviro.assessment.grad001.kamogelotsatsi.controller;

import com.enviro.assessment.grad001.kamogelotsatsi.model.RecyclingTip;
import com.enviro.assessment.grad001.kamogelotsatsi.service.RecyclingTipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/waste/tip")
public class RecyclingTipController {

    private final RecyclingTipService tipService;

    @Autowired
    public RecyclingTipController(RecyclingTipService tipService) {
        this.tipService = tipService;
    }

    @GetMapping
    public List<RecyclingTip> getRecyclingTips() {
        return tipService.getRecyclingTips();
    }

    @GetMapping(path = "{tipId}")
    public ResponseEntity<RecyclingTip> getTipById(@PathVariable("tipId") Long tipId) {
        RecyclingTip tip = tipService.getRecyclingTipById(tipId);
        return ResponseEntity.ok(tip);
    }

    @PostMapping
    public ResponseEntity<?> addNewTip(@Valid @RequestBody RecyclingTip recyclingTip, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        tipService.addNewTip(recyclingTip);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{tipId}")
    public void deleteTip(@PathVariable("tipId") Long tipId) {
        tipService.deleteTip(tipId);
    }

    @PutMapping(path = "{tipId}")
    public ResponseEntity<?> updateTip(
            @PathVariable("tipId") Long tipId,
            @Valid @RequestBody RecyclingTip recyclingTip,
            BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        RecyclingTip updatedTip = tipService.updateTip(tipId, recyclingTip);
        return ResponseEntity.ok(updatedTip);
    }
}
