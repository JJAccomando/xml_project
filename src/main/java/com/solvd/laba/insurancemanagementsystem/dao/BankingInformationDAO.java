package com.solvd.laba.insurancemanagementsystem.dao;

import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.model.BankingInformation;

import java.util.Map;

public interface BankingInformationDAO extends GenericDAO<BankingInformation, Integer> {

    BankingInformation findByCardNum(String cardNum) throws DAOException;

    BankingInformation findByMemberID(Integer ID) throws DAOException;

    Map<Integer, BankingInformation> getBankingInfoMap() throws DAOException;

}
