package com.solvd.laba.insurancemanagementsystem;

import com.solvd.laba.insurancemanagementsystem.factory.DAOFactory;
import com.solvd.laba.insurancemanagementsystem.factory.JAXBHandler;
import com.solvd.laba.insurancemanagementsystem.model.*;
import com.solvd.laba.insurancemanagementsystem.services.AgentService;
import com.solvd.laba.insurancemanagementsystem.services.BankingInformationService;
import com.solvd.laba.insurancemanagementsystem.services.MembersService;
import com.solvd.laba.insurancemanagementsystem.services.PolicyTypeService;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.solvd.laba.insurancemanagementsystem.constants.Constants.MYSQL;

class TestFile {

    private static final InsuranceManagementSystem INSURANCE_MANAGEMENT_SYSTEM;
    private static final List<Members> MEMBERS;
    private static final List<Agent> AGENTS;
    private static final List<BankingInformation> BANKING_INFORMATION;
    private static final List<PolicyType> POLICY_TYPES;

    static {
        INSURANCE_MANAGEMENT_SYSTEM = new InsuranceManagementSystem();
        MEMBERS = new ArrayList<>();
        AGENTS = new ArrayList<>();
        BANKING_INFORMATION = new ArrayList<>();
        POLICY_TYPES = new ArrayList<>();
        setListMembers(INSURANCE_MANAGEMENT_SYSTEM, MEMBERS);
        setListAgents(INSURANCE_MANAGEMENT_SYSTEM, AGENTS);
        setListBankInfo(INSURANCE_MANAGEMENT_SYSTEM, BANKING_INFORMATION);
        setListPolicies(INSURANCE_MANAGEMENT_SYSTEM, POLICY_TYPES);
    }

    private static void setListMembers(InsuranceManagementSystem insuranceManagementSystem, List<Members> members) {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(MYSQL);
        MembersService membersService = new MembersService(mySQLFactory);
        Map<Integer, Members> membersMap = membersService.getAllMembers();
        members = new ArrayList<>(membersMap.values());
        insuranceManagementSystem.setMembersList(members);
    }

    private static void setListAgents(InsuranceManagementSystem insuranceManagementSystem, List<Agent> agents) {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(MYSQL);
        AgentService agentService = new AgentService(mySQLFactory);
        Map<Integer, Agent> agentMap = agentService.getAllAgents();
        agents = new ArrayList<>(agentMap.values());
        insuranceManagementSystem.setAgentList(agents);
    }

    private static void setListBankInfo(InsuranceManagementSystem insuranceManagementSystem, List<BankingInformation> bankInfo) {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(MYSQL);
        BankingInformationService bankingInformationService = new BankingInformationService(mySQLFactory);
        Map<Integer, BankingInformation> bankInfoMap = bankingInformationService.getAllBankingInformationMap();
        bankInfo = new ArrayList<>(bankInfoMap.values());
        insuranceManagementSystem.setBankingInformationList(bankInfo);
    }

    private static void setListPolicies(InsuranceManagementSystem insuranceManagementSystem, List<PolicyType> policyTypes) {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(MYSQL);
        PolicyTypeService policyTypeService = new PolicyTypeService(mySQLFactory);
        Map<Integer, PolicyType> policyMap = policyTypeService.getAllPolicyTypes();
        policyTypes = new ArrayList<>(policyMap.values());
        insuranceManagementSystem.setPolicyTypeList(policyTypes);
    }

    public static void validate() {
        try {
            JAXBHandler.marshal(INSURANCE_MANAGEMENT_SYSTEM);
        } catch (JAXBException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        validate();
    }
}
