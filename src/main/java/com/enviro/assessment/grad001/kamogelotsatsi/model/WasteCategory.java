package com.enviro.assessment.grad001.kamogelotsatsi.model;

import jakarta.persistence.*;

@Entity
@Table
public class WasteCategory {
    @Id
    @SequenceGenerator(
            name = "waste_sequence",
            sequenceName = "waste_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "waste_sequence"
    )

    private Long id;
    @Column(unique = true)
    private String category;

    public WasteCategory(Long id, String category) {
        this.id = id;
        this.category = category;
    }

    public WasteCategory(String category) {
        this.category = category;
    }

    public WasteCategory() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "WasteCategory{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }
}
