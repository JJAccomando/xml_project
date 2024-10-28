package com.solvd.laba.insurancemanagementsystem.dao.jdbc;

import com.solvd.laba.insurancemanagementsystem.dao.BankingInformationDAO;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.model.BankingInformation;

import java.util.Map;

public class OracleBankingInformationDAO implements BankingInformationDAO {
    @Override
    public BankingInformation findByCardNum(String cardNum) throws DAOException {
        return null;
    }

    @Override
    public BankingInformation findByMemberID(Integer ID) throws DAOException {
        return null;
    }

    @Override
    public Map<Integer, BankingInformation> getBankingInfoMap() throws DAOException {
        return null;
    }

    @Override
    public void create(BankingInformation element) throws DAOException {

    }

    @Override
    public BankingInformation findByPrimaryKey(Integer value) throws DAOException {
        return null;
    }
}
