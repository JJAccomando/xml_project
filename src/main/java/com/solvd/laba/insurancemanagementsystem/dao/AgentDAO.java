package com.solvd.laba.insurancemanagementsystem.dao;

import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.model.Agent;

import java.util.Map;

public interface AgentDAO extends GenericDAO<Agent, Integer> {

    Agent findByEmail(String email) throws DAOException;

    Agent findByPhoneNum(String phoneNum) throws DAOException;

}
