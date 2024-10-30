package com.solvd.laba.insurancemanagementsystem.xml;

import com.solvd.laba.insurancemanagementsystem.constants.AgeGroup;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Objects;

public class AgeGroupAdapter extends XmlAdapter<Integer, AgeGroup> {

    @Override
    public AgeGroup unmarshal(Integer id) throws IllegalArgumentException {
        for (AgeGroup group : AgeGroup.values()) {
            if (Objects.equals(group.getId(), id)) {
                return group;
            }
        }
        throw new IllegalArgumentException("Unknown ID: " + id);
    }

    @Override
    public Integer marshal(AgeGroup ageGroup) throws Exception {
        return ageGroup.getId();
    }

}
