package com.solvd.laba.insurancemanagementsystem.xml;

import com.solvd.laba.insurancemanagementsystem.model.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class JAXBHandler {

    public static void marshal(InsuranceManagementSystem object) throws JAXBException, IOException, SAXException {
        JAXBContext ctx = JAXBContext.newInstance(object.getClass());
        Marshaller mar = ctx.createMarshaller();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/main/resources/insurance_management_system.xsd"));
        mar.setSchema(schema);
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(object, new File("src/main/resources/insurance_management_system.xml"));
    }

}
