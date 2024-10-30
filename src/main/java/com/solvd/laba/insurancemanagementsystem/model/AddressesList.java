package com.solvd.laba.insurancemanagementsystem.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Addresses")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressesList {
    @XmlElement(name = "address")
    private Addresses[] addresses;

    public Addresses[] getAddresses() {
        return addresses;
    }

    public void setAddresses(Addresses[] addresses) {
        this.addresses = addresses;
    }

}
