package com.solvd.laba.insurancemanagementsystem.dao;

import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.model.Addresses;

import java.util.Map;

public interface AddressesDAO extends GenericDAO<Addresses, Integer> {

    Map<Integer, Addresses> getAddressesMap() throws DAOException;

}
