package com.solvd.laba.insurancemanagementsystem.dao.jdbc;

import com.solvd.laba.insurancemanagementsystem.dao.MembersDAO;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.model.Members;

import java.sql.SQLException;
import java.util.Map;

public class PostgreSQLMembersDAO implements MembersDAO {
    @Override
    public void create(Members element) throws DAOException {}

    @Override
    public Members findByPrimaryKey(Integer id) throws DAOException {
        return null;
    }

    @Override
    public Members findByEmail(String value) throws DAOException {
        return null;
    }

    @Override
    public Members findByPhoneNum(String value) throws DAOException {
        return null;
    }

    @Override
    public Map<Integer, Members> getMembersMap() throws DAOException {
        return null;
    }

}
