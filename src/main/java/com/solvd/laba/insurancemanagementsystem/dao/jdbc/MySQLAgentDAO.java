package com.solvd.laba.insurancemanagementsystem.dao.jdbc;

import com.solvd.laba.insurancemanagementsystem.connection.DBConnection;
import com.solvd.laba.insurancemanagementsystem.constants.SearchColumn;
import com.solvd.laba.insurancemanagementsystem.dao.AgentDAO;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.model.Agent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.solvd.laba.insurancemanagementsystem.constants.Constants.*;
import static com.solvd.laba.insurancemanagementsystem.utilities.DAOUtilities.prepareStatement;

public class MySQLAgentDAO implements AgentDAO {

    @Override
    public Map<Integer, Agent> getMap() throws DAOException {
        Map<Integer, Agent> agentsMap = new HashMap<>();
        setAgentsMap(agentsMap);
        return agentsMap;
    }

    private void setAgentsMap(Map<Integer, Agent> agentsMap) throws DAOException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = prepareStatement(connection, SQL_FULL_TABLE_AGENT, false)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Agent agent = getAgentFromResultSet(resultSet);
                agentsMap.put(agent.getAgentId(), agent);
            }
        } catch (SQLException e) {
            throw new DAOException("Error: Could not retrieve Agent table information.");
        }
    }

    private Agent getAgentFromResultSet(ResultSet resultSet) throws SQLException {
        Agent agent = new Agent();
        agent.setAgentId(resultSet.getInt("agent_id"));
        agent.setFirstName(resultSet.getString("first_name"));
        agent.setLastName(resultSet.getString("last_name"));
        agent.setEmail(resultSet.getString("email"));
        agent.setPhoneNum(resultSet.getString("phone_number"));
        return agent;
    }

    @Override
    public void create(Agent agent) throws DAOException {
        if (agent.getAgentId() != null) {
            throw new IllegalArgumentException("Agent is already created. Agent already has ID");
        }
        Object[] values = {agent.getFirstName(), agent.getLastName(), agent.getEmail(), agent.getPhoneNum()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = prepareStatement(connection, SQL_INSERT_AGENT, true, values)) {
            statement.executeUpdate();
            try (ResultSet generateKeys = statement.getGeneratedKeys()) {
                if (generateKeys.next()) {
                    agent.setAgentId(generateKeys.getInt(1));
                } else {
                    throw new DAOException("Error: New agent creation failed, no generated key obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error: New agent creation failed.");
        }
    }

    @Override
    public Agent findByPrimaryKey(Integer Id) throws DAOException { return find(Id, SearchColumn.ID); }

    @Override
    public Agent findByEmail(String email) throws DAOException { return find(email, SearchColumn.EMAIL); }

    @Override
    public Agent findByPhoneNum(String phoneNum) throws DAOException { return find(phoneNum, SearchColumn.PHONE_NUM); }

    private Agent find(Object value, SearchColumn searchColumn) throws DAOException, IllegalArgumentException {
        Agent agent = null;
        String sql = getSQLQuery(searchColumn);
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = prepareStatement(connection, sql, false, value);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                agent = getAgentFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Error: Could not retrieve agent information.");
        }
        return agent;
    }

    private String getSQLQuery(SearchColumn column) throws IllegalArgumentException {
        switch (column) {
            case ID:
                return SQL_FIND_BY_ID_AGENT;
            case EMAIL:
                return SQL_FIND_BY_EMAIL_AGENT;
            case PHONE_NUM:
                return SQL_FIND_BY_PHONE_AGENT;
            default:
                throw new IllegalArgumentException("Invalid search column");
        }
    }
}
