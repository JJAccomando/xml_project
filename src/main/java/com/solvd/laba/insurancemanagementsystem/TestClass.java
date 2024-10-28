package com.solvd.laba.insurancemanagementsystem;

import com.solvd.laba.insurancemanagementsystem.model.Addresses;

public class TestClass {
    public static void main(String[] args) {
        Addresses address = new Addresses();
        address.setAddressId(1);
        address.setBuildingNum(4);
        address.setStreet("Rolling Vista Dr.");
        address.setCity("Lomita");
        address.setCountry("U.S.A.");
        address.setPostalCode("90274");
        address.setUnitNum("11");
        address.setState("CA");
        System.out.println(address.toString());
    }
}
