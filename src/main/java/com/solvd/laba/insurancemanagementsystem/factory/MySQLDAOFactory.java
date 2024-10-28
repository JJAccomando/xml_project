package com.solvd.laba.insurancemanagementsystem.factory;

import com.solvd.laba.insurancemanagementsystem.dao.*;
import com.solvd.laba.insurancemanagementsystem.dao.jdbc.*;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;

public class MySQLDAOFactory extends DAOFactory {

    @Override
    public MembersDAO getMembersDAO() throws DAOException {
        return new MySQLMembersDAO();
    }

    @Override
    public AddressesDAO getAddressesDAO() throws DAOException {
        return new MySQLAddressesDAO();
    }

    @Override
    public AgentDAO getAgentDAO() throws DAOException {
        return new MySQLAgentDAO();
    }

    @Override
    public PolicyTypeDAO getPolicyTypeDAO() throws DAOException {
        return new MySQLPolicyTypeDAO();
    }

    @Override
    public BankingInformationDAO getBankingInformationDAO() throws DAOException {
        return new MySQLBankingInformationDAO();
    }

}
