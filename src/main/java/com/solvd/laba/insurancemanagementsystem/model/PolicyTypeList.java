package com.solvd.laba.insurancemanagementsystem.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "policyTypes")
public class PolicyTypeList {

    @XmlElement(name = "policyType")
    private PolicyType[] policyTypes;

    public PolicyType[] getPolicyTypes() {
        return policyTypes;
    }

    public void setPolicyTypes(PolicyType[] policyTypes) {
        this.policyTypes = policyTypes;
    }

}
