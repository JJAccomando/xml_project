package com.solvd.laba.insurancemanagementsystem.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "agent")
@XmlAccessorType(XmlAccessType.FIELD)
public class Agent {
    @XmlAttribute(name = "id")
    private Integer agentId;
    @XmlElement(name = "first_name")
    private String firstName;
    @XmlElement(name = "last_name")
    private String lastName;
    private String email;
    @XmlElement(name = "phone_number")
    private String phoneNum;

    public Integer getAgentId() { return agentId; }

    public void setAgentId(Integer agentId) { this.agentId = agentId; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhoneNum() { return phoneNum; }

    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    @Override
    public boolean equals(Object other) {
        if ((other instanceof Agent) && (agentId != null)) {
            return agentId.equals(((Agent) other).agentId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (agentId != null) {
            return this.getClass().hashCode() + agentId.hashCode();
        }
        return super.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Agent[ID: %d, Name: %s %s, Email: %s, Phone Number: %s]", agentId, firstName, lastName, email, phoneNum);
    }
}
