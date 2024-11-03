package com.solvd.laba.insurancemanagementsystem.constants;

public enum AgeGroup {
    CHILD(1, "child"),
    YOUTH(2, "youth"),
    YOUNG_ADULT(3, "young_adult"),
    ADULT(4, "adult"),
    SENIOR(5, "senior"),
    DEFAULT(6, "no_label");

    AgeGroup(Integer i, String s) { this.id = i; this.label =s;};

    private final Integer id;
    private final String label;

    public Integer getId() { return id; }
    public String getLabel() { return label; }
}
