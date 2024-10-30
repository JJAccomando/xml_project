package com.solvd.laba.insurancemanagementsystem.xml;

import com.solvd.laba.insurancemanagementsystem.model.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;

public class JAXBParser {

    public static <T> void marshal(T object) throws JAXBException, IOException {
        JAXBContext ctx = JAXBContext.newInstance(object.getClass());
        Marshaller mar = ctx.createMarshaller();
        mar.marshal(object, new File("insurance_management_system.xml"));
    }

    public static Addresses unmarshal(File file) throws JAXBException, IOException {
        JAXBContext ctx = JAXBContext.newInstance(Addresses.class);
        return (Addresses)ctx.createUnmarshaller().unmarshal(file);
    }

    /*public static void marshal(Agent agent) throws JAXBException, IOException {
        JAXBContext ctx = JAXBContext.newInstance(Agent.class);
        Marshaller mar = ctx.createMarshaller();
        mar.marshal(agent, new File("insurance_management_system.xml"));
    }

    public static void marshal(BankingInformation bankInfo) throws JAXBException, IOException {
        JAXBContext ctx = JAXBContext.newInstance(BankingInformation.class);
        Marshaller mar = ctx.createMarshaller();
        mar.marshal(bankInfo, new File("insurance_management_system.xml"));
    }

    public static void marshal(Members member) throws JAXBException, IOException {
        JAXBContext ctx = JAXBContext.newInstance(Members.class);
        Marshaller mar = ctx.createMarshaller();
        mar.marshal(member, new File("insurance_management_system.xml"));
    }

    public static void marshal(PolicyType policyType) throws JAXBException, IOException {
        JAXBContext ctx = JAXBContext.newInstance(PolicyType.class);
        Marshaller mar = ctx.createMarshaller();
        mar.marshal(policyType, new File("insurance_management_system.xml"));
    }*/




}
