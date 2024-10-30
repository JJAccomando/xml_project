package com.solvd.laba.insurancemanagementsystem.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "agents")
@XmlAccessorType(XmlAccessType.FIELD)
public class AgentList {

    @XmlElement(name = "agent")
    private Agent[] agents;

    public Agent[] getAgents() {
        return agents;
    }

    public void setAgents(Agent[] agents) {
        this.agents = agents;
    }

}
