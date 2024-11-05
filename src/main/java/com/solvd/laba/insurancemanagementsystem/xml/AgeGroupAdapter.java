package com.solvd.laba.insurancemanagementsystem.xml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.laba.insurancemanagementsystem.constants.AgeGroup;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Objects;

@XmlRootElement(name = "age_group_adapter")
public class AgeGroupAdapter {

    @JsonIgnore
    private AgeGroup ageGroup;
    @XmlTransient
    @JsonProperty("id")
    private Integer id;
    @XmlTransient
    @JsonProperty("group_label")
    private String groupLabel;

    public AgeGroupAdapter() {
        this.ageGroup = AgeGroup.DEFAULT;
    }

    public AgeGroupAdapter(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
        this.id = ageGroup.getId();
        this.groupLabel = ageGroup.getLabel();
    }

    @XmlAttribute(name = "id")
    public Integer getAgeGroupId() {
        return ageGroup.getId();
    }

    @XmlElement(name = "group_label")
    public String getAgeGroupLabel() {
        return ageGroup.getLabel();
    }

    public void setAgeGroupId(Integer id) {
        for (AgeGroup ag : AgeGroup.values()) {
            if (ag.getId().equals(id)) {
                this.ageGroup = ag;
                break;
            }
        }
        this.id = id;
    }

    public void setAgeGroupLabel(String label) {
        for (AgeGroup ag : AgeGroup.values()) {
            if (ag.getLabel().equals(label)) {
                this.ageGroup = ag;
                break;
            }
        }
        this.groupLabel = label;
    }
}
