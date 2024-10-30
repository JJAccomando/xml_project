package com.solvd.laba.insurancemanagementsystem.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bankingInformation")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankingInformationList {

    @XmlElement(name = "bankingInformation")
    private BankingInformation[] bankingInformationList;

    public BankingInformation[] getBankingInformationList() {
        return bankingInformationList;
    }

    public void setBankingInformationList(BankingInformation[] bankingInformationList) {
        this.bankingInformationList = bankingInformationList;
    }

}
