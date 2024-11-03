package com.solvd.laba.insurancemanagementsystem.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "insurance_management_system")
@XmlAccessorType(XmlAccessType.FIELD)
public class InsuranceManagementSystem {
    @XmlElementWrapper(name = "members")
    @XmlElement(name = "member")
    private List<Members> membersList;
    @XmlElementWrapper(name = "agents")
    @XmlElement(name = "agent")
    private List<Agent> agentList;
    @XmlElementWrapper(name = "banking_information")
    @XmlElement(name = "member_banking_information")
    private List<BankingInformation> bankingInformationList;
    @XmlElementWrapper(name = "policy_types")
    @XmlElement(name = "policy_type")
    private List<PolicyType> policyTypeList;

    public List<Members> getMembersList() {
        return membersList;
    }

    public void setMembersList(List<Members> membersList) {
        this.membersList = membersList;
    }

    public List<Agent> getAgentList() {
        return agentList;
    }

    public void setAgentList(List<Agent> agentList) {
        this.agentList = agentList;
    }

    public List<BankingInformation> getBankingInformationList() {
        return bankingInformationList;
    }

    public void setBankingInformationList(List<BankingInformation> bankingInformationList) {
        this.bankingInformationList = bankingInformationList;
    }

    public List<PolicyType> getPolicyTypeList() {
        return policyTypeList;
    }

    public void setPolicyTypeList(List<PolicyType> policyTypeList) {
        this.policyTypeList = policyTypeList;
    }
}
