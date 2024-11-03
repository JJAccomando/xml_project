package com.solvd.laba.insurancemanagementsystem.factory;

import com.solvd.laba.insurancemanagementsystem.model.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class CustomSAXHandler extends DefaultHandler {

    private StringBuilder data = null;
    private final List<Members> members = new ArrayList<>();
    private final List<BankingInformation> bankInfoList = new ArrayList<>();
    private final List<Agent> agents = new ArrayList<>();
    private final List<PolicyType> policies = new ArrayList<>();
    private Members member = null;
    private Agent agent = null;
    private BankingInformation bankInfo = null;
    private Addresses address = null;
    private PolicyType policyType = null;

    public List<Members> getMembersList() {
        return members;
    }

    public List<BankingInformation> getBankInfoList() {
        return bankInfoList;
    }

    public List<Agent> getAgentsList() {
        return agents;
    }

    public List<PolicyType> getPoliciesList() {
        return policies;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName.toLowerCase()) {
            case "member":
            case "banking_member":
                member = XMLHelper.createMember(attributes);
                break;
            case "member_banking_information":
                bankInfo = XMLHelper.createBankingInformation(attributes);
                break;
            case "primary_address":
            case "billing_address":
                address = XMLHelper.createAddress(attributes);
                break;
            case "agent":
                agent = XMLHelper.createAgent(attributes);
                break;
            case "policy_type":
                policyType = XMLHelper.createPolicyType(attributes);
                break;
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (address != null) {
            XMLHelper.populateAddresses(address, qName, data.toString());
            if (qName.equalsIgnoreCase("primary_address")) {
                member.setAddress(address);
            } else if (qName.equalsIgnoreCase("billing_address")) {
                bankInfo.setBillingAddress(address);
            }
            address = null;
        }
        if (member != null) {
            XMLHelper.populateMember(member, qName, data.toString());
            if (qName.equalsIgnoreCase("member")) {
                members.add(member);
            } else if (qName.equalsIgnoreCase("banking_member")) {
                bankInfo.setBankingMember(member);
            }
            member = null;
        }
        if (bankInfo != null) {
            XMLHelper.populateBankingInformation(bankInfo, qName, data.toString());
            bankInfoList.add(bankInfo);
            bankInfo = null;
        }
        if (agent != null) {
            XMLHelper.populateAgent(agent, qName, data.toString());
            agents.add(agent);
            agent = null;
        }
        if (policyType != null) {
            XMLHelper.populatePolicyType(policyType, qName, data.toString());
            policies.add(policyType);
            policyType = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (data != null) {
            data.append(new String(ch, start, length));
        }
    }

    static class XMLHelper {

        public static Members createMember(Attributes attributes) {
            Members member = new Members();
            String id = attributes.getValue("id");
            if (id != null) {
                member.setId(Integer.parseInt(id));
            }
            return member;
        }

        public static Addresses createAddress(Attributes attributes) {
            Addresses address = new Addresses();
            String id = attributes.getValue("id");
            if (id != null) {
                address.setAddressId(Integer.parseInt(id));
            }
            return address;
        }

        public static Agent createAgent(Attributes attributes) {
            Agent agent = new Agent();
            String id = attributes.getValue("id");
            if (id != null) {
                agent.setAgentId(Integer.parseInt(id));
            }
            return agent;
        }

        public static BankingInformation createBankingInformation(Attributes attributes) {
            BankingInformation bankingInformation = new BankingInformation();
            String id = attributes.getValue("id");
            if (id != null) {
                bankingInformation.setBankingId(Integer.parseInt(id));
            }
            return bankingInformation;
        }

        public static PolicyType createPolicyType(Attributes attributes) {
            PolicyType policyType = new PolicyType();
            String id = attributes.getValue("id");
            if (id != null) {
                policyType.setPolicyTypeId(Integer.parseInt(id));
            }
            return policyType;
        }

        public static void populateMember(Members member, String qName, String data) {
            if (qName.equalsIgnoreCase("first_name")) {
                member.setFirstName(data);
            } else if (qName.equalsIgnoreCase("last_name")) {
                member.setLastName(data);
            } else if (qName.equalsIgnoreCase("email")) {
                member.setEmail(data);
            } else if (qName.equalsIgnoreCase("phone_number")) {
                member.setPhoneNum(data);
            } else if (qName.equalsIgnoreCase("date_of_birth")) {
                member.setDateOfBirth(data);
            }
        }

        public static void populateAddresses(Addresses address, String qName, String data) {
            if (qName.equalsIgnoreCase("building_num")) {
                address.setBuildingNum(Integer.parseInt(data));
            } else if (qName.equalsIgnoreCase("unit_number")) {
                address.setUnitNum(data);
            } else if (qName.equalsIgnoreCase("street")) {
                address.setStreet(data);
            } else if (qName.equalsIgnoreCase("city")) {
                address.setCity(data);
            } else if (qName.equalsIgnoreCase("state")) {
                address.setState(data);
            } else if (qName.equalsIgnoreCase("country")) {
                address.setCountry(data);
            } else if (qName.equalsIgnoreCase("postal_code")) {
                address.setPostalCode(data);
            }
        }

        public static void populateAgent(Agent agent, String qName, String data) {
            if (qName.equalsIgnoreCase("firs_name")) {
                agent.setFirstName(data);
            } else if (qName.equalsIgnoreCase("last_name")) {
                agent.setLastName(data);
            } else if (qName.equalsIgnoreCase("email")) {
                agent.setEmail(data);
            } else if (qName.equalsIgnoreCase("phone_number")) {
                agent.setPhoneNum(data);
            }
        }

        public static void populateBankingInformation(BankingInformation bankingInformation, String qName, String data) {
            if (qName.equalsIgnoreCase("card_number")) {
                bankingInformation.setCardNum(data);
            } else if (qName.equalsIgnoreCase("card_type")) {
                bankingInformation.setCardType(data);
            }
        }

        public static void populatePolicyType(PolicyType policyType, String qName, String data) {
            if (qName.equalsIgnoreCase("type_name")) {
                policyType.setTypeName(data);
            } else if (qName.equalsIgnoreCase("policy_description")) {
                policyType.setPolicyDescription(data);
            }
        }
    }

}
