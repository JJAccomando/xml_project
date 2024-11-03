package com.solvd.laba.insurancemanagementsystem;


import com.solvd.laba.insurancemanagementsystem.factory.CustomSAXHandler;
import com.solvd.laba.insurancemanagementsystem.factory.DAOFactory;
import com.solvd.laba.insurancemanagementsystem.model.*;
import com.solvd.laba.insurancemanagementsystem.services.*;
import com.solvd.laba.insurancemanagementsystem.factory.JAXBHandler;
import org.junit.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.solvd.laba.insurancemanagementsystem.constants.Constants.MYSQL;

public class DAOTest {

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
        setListObject(MEMBERS, "members");
        setListObject(AGENTS, "agents");
        setListObject(BANKING_INFORMATION, "banking_information");
        setListObject(POLICY_TYPES, "policy_types");
    }

    private static <T> void setListObject(List<T> objectList, String list) {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(MYSQL);
        switch (list) {
            case "members":
                MembersService membersService = new MembersService(mySQLFactory);
                Map<Integer, Members> membersMap = membersService.getAllMembers();
                objectList = (List<T>) new ArrayList<>(membersMap.values());
                break;
            case "agents":
                AgentService agentService = new AgentService(mySQLFactory);
                Map<Integer, Agent> agentMap = agentService.getAllAgents();
                objectList = (List<T>) new ArrayList<>(agentMap.values());
                break;
            case "banking_information":
                BankingInformationService bankingInformationService = new BankingInformationService(mySQLFactory);
                Map<Integer, BankingInformation> bankInfoMap = bankingInformationService.getAllBankingInformationMap();
                objectList = (List<T>) new ArrayList<>(bankInfoMap.values());
                break;
            case "policy_types":
                PolicyTypeService policyTypeService = new PolicyTypeService(mySQLFactory);
                Map<Integer, PolicyType> policyMap = policyTypeService.getAllPolicyTypes();
                objectList = (List<T>) new ArrayList<>(policyMap.values());
                break;
        }
    }

    @Test
    public void parseXML() throws JAXBException, IOException, SAXException {
        try {
            JAXBHandler.marshal(INSURANCE_MANAGEMENT_SYSTEM);
        } catch (JAXBException | IOException | SAXException e) {
            e.printStackTrace();
        }
        checkXML(new File("insurance_management_system.xml"));
    }

    private void checkXML(File file) {
        CustomSAXHandler handler = new CustomSAXHandler();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(false);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("insurance_management_system.xsd"));
            factory.setSchema(schema);
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals("Members must match", handler.getMembersList(), MEMBERS);
        Assert.assertEquals("Agents must match", handler.getAgentsList(), AGENTS);
        Assert.assertEquals("Banking Information must match", handler.getBankInfoList(), BANKING_INFORMATION);
        Assert.assertEquals("Policies must match", handler.getPoliciesList(), POLICY_TYPES);
    }

}
