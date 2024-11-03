package com.solvd.laba.insurancemanagementsystem.services;

import com.solvd.laba.insurancemanagementsystem.dao.BankingInformationDAO;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.factory.DAOFactory;
import com.solvd.laba.insurancemanagementsystem.model.BankingInformation;

import java.util.Map;

public class BankingInformationService {

    private final BankingInformationDAO bankingInformationDAO;

    public BankingInformationService(DAOFactory daoFactory) throws DAOException {
        this.bankingInformationDAO = daoFactory.getBankingInformationDAO();
    }

    public void createMembers(BankingInformation bankInfo) throws DAOException {
        try {
            bankingInformationDAO.create(bankInfo);
        } catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public BankingInformation bankInfoById(Integer id) throws DAOException {
        try {
            return bankingInformationDAO.findByPrimaryKey(id);
        } catch (IllegalArgumentException | DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public BankingInformation bankInfoByCardNumber(String cardNum) throws DAOException {
        try {
            return bankingInformationDAO.findByCardNum(cardNum);
        } catch (IllegalArgumentException | DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public BankingInformation bankInfoByBankMemberId(Integer id) throws DAOException {
        try {
            return bankingInformationDAO.findByMemberID(id);
        } catch (IllegalArgumentException | DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public Map<Integer, BankingInformation> getAllBankingInformationMap() throws DAOException {
        try {
            return bankingInformationDAO.getMap();
        } catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

}
