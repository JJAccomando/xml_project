package com.solvd.laba.insurancemanagementsystem.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "members")
@XmlAccessorType(XmlAccessType.FIELD)
public class MembersList {

    @XmlElement(name = "member")
    private Members[] members;

    public Members[] getMembers() {
        return members;
    }

    public void setMembers(Members[] members) {
        this.members = members;
    }

}
