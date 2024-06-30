package com.enviro.assessment.grad001.kamogelotsatsi.model;

import jakarta.persistence.*;

@Entity
@Table
public class DisposalGuideline {
    @Id
    @SequenceGenerator(
            name = "guideline_sequence",
            sequenceName = "guideline_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "guideline_sequence"
    )

    private Long id;

    public String getWasteCategory() {
        return wasteCategory;
    }

    public void setWasteCategory(String wasteCategory) {
        this.wasteCategory = wasteCategory;
    }


    private String guideline;
    private String wasteCategory;

    public DisposalGuideline(Long id, String guideline, String wasteCategory) {
        this.id = id;
        this.guideline = guideline;
        this.wasteCategory = wasteCategory;
    }

    public DisposalGuideline(String guideline, String wasteCategory) {
        this.guideline = guideline;
        this.wasteCategory = wasteCategory;
    }

    public DisposalGuideline() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuideline() {
        return guideline;
    }

    public void setGuideline(String guideline) {
        this.guideline = guideline;
    }


    public void setString(String wasteCategory) {
        this.wasteCategory = wasteCategory;
    }

    @Override
    public String toString() {
        return "DisposalGuideline{" +
                "id=" + id +
                ", guideline='" + guideline + '\'' +
                ", category=" + wasteCategory +
                '}';
    }
}
