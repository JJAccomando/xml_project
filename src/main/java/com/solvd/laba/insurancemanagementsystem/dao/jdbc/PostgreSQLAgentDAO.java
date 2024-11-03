package com.solvd.laba.insurancemanagementsystem.dao.jdbc;

import com.solvd.laba.insurancemanagementsystem.dao.AgentDAO;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.model.Agent;

import java.util.Map;

public class PostgreSQLAgentDAO implements AgentDAO {
    @Override
    public Map<Integer, Agent> getMap() throws DAOException {
        return null;
    }

    @Override
    public Agent findByEmail(String email) throws DAOException {
        return null;
    }

    @Override
    public Agent findByPhoneNum(String phoneNum) throws DAOException {
        return null;
    }

    @Override
    public void create(Agent element) throws DAOException {

    }

    @Override
    public Agent findByPrimaryKey(Integer value) throws DAOException {
        return null;
    }
}
