package com.solvd.laba.insurancemanagementsystem.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "policy_type")
@XmlAccessorType(XmlAccessType.FIELD)
public class PolicyType {
    @XmlAttribute(name = "id")
    private Integer policyTypeId;
    @XmlElement(name = "type_name")
    private String typeName;
    @XmlElement(name = "policy_description")
    private String policyDescription;

    public Integer getPolicyTypeId() { return policyTypeId; }

    public void setPolicyTypeId(Integer policyTypeId) { this.policyTypeId = policyTypeId; }

    public String getTypeName() { return typeName; }

    public void setTypeName(String typeName) { this.typeName = typeName; }

    public String getPolicyDescription() { return policyDescription; }

    public void setPolicyDescription(String policyDescription) { this.policyDescription = policyDescription; }

    @Override
    public boolean equals(Object other) {
        if ((other instanceof PolicyType) && (policyTypeId != null)) {
            return policyTypeId.equals(((PolicyType) other).policyTypeId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (policyTypeId != null) {
            return this.getClass().hashCode() + policyTypeId.hashCode();
        }
        return super.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Policy[ID: %d, Coverage Type: %s, Description: %s]", policyTypeId, typeName, policyDescription);
    }
}
