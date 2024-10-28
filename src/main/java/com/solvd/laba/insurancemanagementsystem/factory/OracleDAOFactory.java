package com.solvd.laba.insurancemanagementsystem.factory;

import com.solvd.laba.insurancemanagementsystem.dao.*;
import com.solvd.laba.insurancemanagementsystem.dao.jdbc.*;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;

public class OracleDAOFactory extends DAOFactory {

    @Override
    public MembersDAO getMembersDAO() throws DAOException {
        return new OracleMembersDAO();
    }

    @Override
    public AddressesDAO getAddressesDAO() throws DAOException {
        return new OracleAddressesDAO();
    }

    @Override
    public AgentDAO getAgentDAO() throws DAOException {
        return new OracleAgentDAO();
    }

    @Override
    public PolicyTypeDAO getPolicyTypeDAO() throws DAOException {
        return new OraclePolicyTypeDAO();
    }

    @Override
    public BankingInformationDAO getBankingInformationDAO() throws DAOException {
        return new OracleBankingInformationDAO();
    }


}
