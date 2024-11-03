package com.solvd.laba.insurancemanagementsystem.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Addresses {
    @XmlAttribute(name = "id")
    private Integer addressId;
    @XmlElement(name = "building_number")
    private Integer buildingNum;
    @XmlElement(name = "unit_number")
    private String unitNum;
    private String street;
    private String city;
    private String state;
    private String country;
    @XmlElement(name = "postal_code")
    private String postalCode;

    public Integer getAddressId() { return addressId; }

    public void setAddressId(Integer addressId) { this.addressId = addressId; }

    public Integer getBuildingNum() { return buildingNum; }

    public void setBuildingNum(Integer buildingNum) { this.buildingNum = buildingNum; }

    public String getUnitNum() { return unitNum; }

    public void setUnitNum(String unitNum) { this.unitNum = unitNum; }

    public String getStreet() { return street; }

    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public String getPostalCode() { return postalCode; }

    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    @Override
    public boolean equals(Object other) {
        if ((other instanceof Addresses) && (addressId != null)) {
            return addressId.equals(((Addresses) other).addressId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (addressId != null) {
            return this.getClass().hashCode() + addressId.hashCode();
        }
        return super.hashCode();
    }

    @Override
    public String toString() {
        String getUnit = (unitNum == null) ? "N/A" : unitNum;
        String getState = (state == null) ? "N/A" : state;
        return String.format("Address[ID: %d, Building Number: %d Street: %s, Unit: %s, City: %s, State: %s, Country: %s, Postal Code: %s]",
                addressId, buildingNum, street, getUnit, city, getState, country, postalCode);
    }
}
