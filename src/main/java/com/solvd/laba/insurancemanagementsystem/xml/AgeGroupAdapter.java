package com.solvd.laba.insurancemanagementsystem.xml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.laba.insurancemanagementsystem.constants.AgeGroup;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Objects;

@XmlRootElement(name = "age_group_adapter")
public class AgeGroupAdapter {

    private final AgeGroup ageGroup;

    public AgeGroupAdapter() {
        this.ageGroup = AgeGroup.DEFAULT;
    }

    public AgeGroupAdapter(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    @XmlAttribute(name = "id")
    @JsonProperty("id")
    public Integer getAgeGroupId() {
        return ageGroup.getId();
    }

    @XmlElement(name = "group_label")
    @JsonProperty("group_label")
    public String getAgeGroupLabel() {
        return ageGroup.getLabel();
    }
}
