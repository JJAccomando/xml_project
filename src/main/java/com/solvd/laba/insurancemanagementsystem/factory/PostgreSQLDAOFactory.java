package com.solvd.laba.insurancemanagementsystem.factory;

import com.solvd.laba.insurancemanagementsystem.dao.*;
import com.solvd.laba.insurancemanagementsystem.dao.jdbc.*;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;

public class PostgreSQLDAOFactory extends DAOFactory {
    @Override
    public MembersDAO getMembersDAO() throws DAOException {
        return new PostgreSQLMembersDAO();
    }

    @Override
    public AddressesDAO getAddressesDAO() throws DAOException {
        return new PostgreSQLAddressesDAO();
    }

    @Override
    public AgentDAO getAgentDAO() throws DAOException {
        return new PostgreSQLAgentDAO();
    }

    @Override
    public PolicyTypeDAO getPolicyTypeDAO() throws DAOException {
        return new PostgreSQLPolicyTypeDAO();
    }

    @Override
    public BankingInformationDAO getBankingInformationDAO() throws DAOException {
        return new PostgreSQLBankingInformationDAO();
    }

}
