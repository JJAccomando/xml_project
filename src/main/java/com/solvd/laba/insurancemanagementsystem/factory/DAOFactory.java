package com.solvd.laba.insurancemanagementsystem.factory;

import com.solvd.laba.insurancemanagementsystem.dao.*;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;

import static com.solvd.laba.insurancemanagementsystem.constants.Constants.*;

public abstract class DAOFactory {

    public abstract MembersDAO getMembersDAO() throws DAOException;

    public abstract AddressesDAO getAddressesDAO() throws DAOException;

    public abstract AgentDAO getAgentDAO() throws DAOException;

    public abstract PolicyTypeDAO getPolicyTypeDAO() throws DAOException;

    public abstract BankingInformationDAO getBankingInformationDAO() throws DAOException;

    public static DAOFactory getDAOFactory(int factoryType) throws IllegalArgumentException {
        switch (factoryType) {
            case MYSQL:
                return new MySQLDAOFactory();
            case ORACLE:
                return new OracleDAOFactory();
            case POSTGRESQL:
                return new PostgreSQLDAOFactory();
            default:
                throw new IllegalArgumentException("Error: Invalid framework.");
        }
    }
}
