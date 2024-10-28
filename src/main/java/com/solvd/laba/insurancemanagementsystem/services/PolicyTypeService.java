package com.solvd.laba.insurancemanagementsystem.services;

import com.solvd.laba.insurancemanagementsystem.dao.PolicyTypeDAO;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.factory.DAOFactory;
import com.solvd.laba.insurancemanagementsystem.model.PolicyType;

import java.util.Map;

public class PolicyTypeService {

    private final PolicyTypeDAO policyTypeDAO;

    public PolicyTypeService(DAOFactory daoFactory) throws DAOException {
        this.policyTypeDAO = daoFactory.getPolicyTypeDAO();
    }

    public void createPolicy(PolicyType policyType) throws DAOException {
        try {
            policyTypeDAO.create(policyType);
        } catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public PolicyType policyById(Integer id) throws DAOException {
        try {
            return policyTypeDAO.findByPrimaryKey(id);
        } catch (IllegalArgumentException | DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public PolicyType policyByName(String name) throws DAOException {
        try {
            return policyTypeDAO.findByTypeName(name);
        } catch (IllegalArgumentException | DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public Map<Integer, PolicyType> getAllPolicyTypes() throws DAOException {
        try {
            return policyTypeDAO.getPoliciesMap();
        } catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

}
