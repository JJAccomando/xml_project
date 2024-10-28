package com.solvd.laba.insurancemanagementsystem.dao.jdbc;

import com.solvd.laba.insurancemanagementsystem.dao.AddressesDAO;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.model.Addresses;

import java.util.Map;

public class OracleAddressesDAO implements AddressesDAO {
    @Override
    public Map<Integer, Addresses> getAddressesMap() throws DAOException {
        return null;
    }

    @Override
    public void create(Addresses element) throws DAOException {
    }

    @Override
    public Addresses findByPrimaryKey(Integer value) throws DAOException {
        return null;
    }
}
