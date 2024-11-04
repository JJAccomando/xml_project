package com.solvd.laba.insurancemanagementsystem;

import com.solvd.laba.insurancemanagementsystem.factory.DAOFactory;
import com.solvd.laba.insurancemanagementsystem.json.JacksonParser;
import com.solvd.laba.insurancemanagementsystem.model.*;
import com.solvd.laba.insurancemanagementsystem.services.AgentService;
import com.solvd.laba.insurancemanagementsystem.services.BankingInformationService;
import com.solvd.laba.insurancemanagementsystem.services.MembersService;
import com.solvd.laba.insurancemanagementsystem.services.PolicyTypeService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.solvd.laba.insurancemanagementsystem.constants.Constants.MYSQL;

public class TestFile {

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
        members.addAll(membersMap.values());
        insuranceManagementSystem.setMembersList(members);
    }

    private static void setListAgents(InsuranceManagementSystem insuranceManagementSystem, List<Agent> agents) {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(MYSQL);
        AgentService agentService = new AgentService(mySQLFactory);
        Map<Integer, Agent> agentMap = agentService.getAllAgents();
        agents.addAll(agentMap.values());
        insuranceManagementSystem.setAgentList(agents);
    }

    private static void setListBankInfo(InsuranceManagementSystem insuranceManagementSystem, List<BankingInformation> bankInfo) {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(MYSQL);
        BankingInformationService bankingInformationService = new BankingInformationService(mySQLFactory);
        Map<Integer, BankingInformation> bankInfoMap = bankingInformationService.getAllBankingInformationMap();
        bankInfo.addAll(bankInfoMap.values());
        insuranceManagementSystem.setBankingInformationList(bankInfo);
    }

    private static void setListPolicies(InsuranceManagementSystem insuranceManagementSystem, List<PolicyType> policyTypes) {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(MYSQL);
        PolicyTypeService policyTypeService = new PolicyTypeService(mySQLFactory);
        Map<Integer, PolicyType> policyMap = policyTypeService.getAllPolicyTypes();
        policyTypes.addAll(policyMap.values());
        insuranceManagementSystem.setPolicyTypeList(policyTypes);
    }

    public static void main(String[] args) {
        InsuranceManagementSystem newSystem = new InsuranceManagementSystem();
        try {
            JacksonParser.write(INSURANCE_MANAGEMENT_SYSTEM);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            newSystem = JacksonParser.read(new File("insurance_management_system.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
