package com.solvd.laba.insurancemanagementsystem.services;

import com.solvd.laba.insurancemanagementsystem.dao.AgentDAO;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.factory.DAOFactory;
import com.solvd.laba.insurancemanagementsystem.model.Agent;

import java.util.Map;

public class AgentService {

    private final AgentDAO agentDAO;

    public AgentService(DAOFactory daoFactory) throws DAOException {
        this.agentDAO = daoFactory.getAgentDAO();
    }

    public void createAgent(Agent agent) throws DAOException {
        try {
            agentDAO.create(agent);
        } catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public Agent agentById(Integer id) throws DAOException {
        try {
            return agentDAO.findByPrimaryKey(id);
        } catch (IllegalArgumentException | DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public Agent agentByEmail(String email) throws DAOException {
        try {
            return agentDAO.findByEmail(email);
        } catch (IllegalArgumentException | DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public Agent agentByPhoneNum(String phoneNum) throws DAOException {
        try {
            return agentDAO.findByPhoneNum(phoneNum);
        } catch (IllegalArgumentException | DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public Map<Integer, Agent> getAllAgents() throws DAOException {
        try {
            return agentDAO.getMap();
        } catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

}
