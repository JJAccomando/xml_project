package com.solvd.laba.insurancemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.solvd.laba.insurancemanagementsystem.constants.AgeGroup;
import com.solvd.laba.insurancemanagementsystem.xml.AgeGroupAdapter;
import com.solvd.laba.insurancemanagementsystem.xml.DateAdapter;

import java.beans.Transient;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "member")
@XmlAccessorType(XmlAccessType.FIELD)
public class Members {
    @XmlAttribute(name = "id")
    @JsonProperty("id")
    private Integer memberId;
    @XmlElement(name = "first_name")
    @JsonProperty("first_name")
    private String firstName;
    @XmlElement(name = "last_name")
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @XmlElement(name = "phone_number")
    @JsonProperty("phone_number")
    private String phoneNum;
    @XmlElement(name = "date_of_birth")
    @XmlJavaTypeAdapter(DateAdapter.class)
    @JsonProperty("date_of_birth")
    private Date dateOfBirth;
    @XmlElement(name = "primary_address")
    @JsonProperty("primary_address")
    private Addresses address;
    @XmlTransient
    @JsonIgnore
    private AgeGroup ageGroup;
    @XmlElement(name = "age_group")
    @JsonProperty("age_group")
    private AgeGroupAdapter ageGroupAdapter;

    public Integer getMemberId() { return memberId; }

    public void setId(Integer memberId) { this.memberId = memberId; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName;}

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhoneNum() { return phoneNum; }

    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    public Addresses getAddress() {return address; }

    public void setAddress(Addresses address) { this.address = address; }

    public java.sql.Date getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = Date.valueOf(dateOfBirth);
        int age = calculateAge(this.getDateOfBirth());
        this.ageGroup = determineAgeGroup(age);
        this.ageGroupAdapter = new AgeGroupAdapter(ageGroup);
    }

    public AgeGroup getAgeGroup() { return ageGroup; }

    public AgeGroupAdapter getAgeGroupAdapter() { return ageGroupAdapter; }

    private static int calculateAge(Date date) {
        LocalDate localDate = date.toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(localDate, currentDate).getYears();
    }

    private static AgeGroup determineAgeGroup(int age) {
        if (age < 13) {
            return AgeGroup.CHILD;
        } else if (age < 18) {
            return AgeGroup.YOUTH;
        } else if (age < 26) {
            return AgeGroup.YOUNG_ADULT;
        } else if (age < 65) {
            return AgeGroup.ADULT;
        } else {
            return AgeGroup.SENIOR;
        }
    }

    @Override
    public boolean equals(Object other) {
        if ((other instanceof Members) && (memberId != null)) {
            return memberId.equals(((Members) other).memberId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (memberId != null) {
            return this.getClass().hashCode() + memberId.hashCode();
        }
        return super.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Member[ID: %d, Name: %s %s, Email: %s, Phone Number: %s, Date of Birth: %s]", memberId, firstName, lastName, email, phoneNum, dateOfBirth);
    }
}
