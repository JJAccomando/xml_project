package com.solvd.laba.insurancemanagementsystem.dao.jdbc;

import com.solvd.laba.insurancemanagementsystem.dao.PolicyTypeDAO;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.model.PolicyType;

import java.util.Map;

public class OraclePolicyTypeDAO implements PolicyTypeDAO {

    @Override
    public void create(PolicyType element) throws DAOException {

    }

    @Override
    public PolicyType findByPrimaryKey(Integer value) throws DAOException {
        return null;
    }

    @Override
    public PolicyType findByTypeName(String name) throws DAOException {
        return null;
    }

    @Override
    public Map<Integer, PolicyType> getPoliciesMap() throws DAOException {
        return null;
    }

}
