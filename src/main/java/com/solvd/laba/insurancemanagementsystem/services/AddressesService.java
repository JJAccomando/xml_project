package com.solvd.laba.insurancemanagementsystem.services;

import com.solvd.laba.insurancemanagementsystem.dao.AddressesDAO;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.factory.DAOFactory;
import com.solvd.laba.insurancemanagementsystem.model.Addresses;

import java.util.Map;

public class AddressesService {

    private final AddressesDAO addressesDAO;

    public AddressesService(DAOFactory daoFactory) throws DAOException {
        this.addressesDAO = daoFactory.getAddressesDAO();
    }

    public void createAddress(Addresses address) throws DAOException {
        try {
            addressesDAO.create(address);
        } catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public Addresses addressesById(Integer id) throws DAOException {
        try {
            return addressesDAO.findByPrimaryKey(id);
        } catch (IllegalArgumentException | DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public Map<Integer, Addresses> getAddressesMap() throws DAOException {
        try {
            return addressesDAO.getMap();
        } catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

}
