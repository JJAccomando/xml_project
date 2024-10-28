package com.solvd.laba.insurancemanagementsystem.dao;

import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.model.Members;

import java.sql.SQLException;
import java.util.Map;

public interface MembersDAO extends GenericDAO<Members, Integer> {

    Members findByEmail(String email) throws DAOException;

    Members findByPhoneNum(String phoneNum) throws DAOException;

    Map<Integer, Members> getMembersMap() throws DAOException;
}
