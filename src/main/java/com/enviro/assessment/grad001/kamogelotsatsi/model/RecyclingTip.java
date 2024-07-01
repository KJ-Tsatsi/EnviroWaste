package com.enviro.assessment.grad001.kamogelotsatsi.model;

import jakarta.persistence.*;

@Entity
@Table
public class RecyclingTip {
    @Id
    @SequenceGenerator(
            name = "tip_sequence",
            sequenceName = "tip_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tip_sequence"
    )

    private Long id;
    private String tip;

    public RecyclingTip(String tip) {
        this.tip = tip;
    }

    public RecyclingTip(Long id, String tip) {
        this.id = id;
        this.tip = tip;
    }

    public RecyclingTip() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "RecyclingTip{" +
                "id=" + id +
                ", tip='" + tip + '\'' +
                '}';
    }
}
