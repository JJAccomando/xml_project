package com.solvd.laba.insurancemanagementsystem.dao.jdbc;

import com.solvd.laba.insurancemanagementsystem.connection.DBConnection;
import com.solvd.laba.insurancemanagementsystem.constants.SearchColumn;
import com.solvd.laba.insurancemanagementsystem.dao.PolicyTypeDAO;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.model.PolicyType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.solvd.laba.insurancemanagementsystem.constants.Constants.*;
import static com.solvd.laba.insurancemanagementsystem.utilities.DAOUtilities.prepareStatement;

public class MySQLPolicyTypeDAO implements PolicyTypeDAO {

    @Override
    public void create(PolicyType policy) throws DAOException {
        if (policy.getPolicyTypeId() != null) {
            throw new IllegalArgumentException("Policy type is already created. Policy type already has ID");
        }
        Object[] values = {policy.getTypeName(), policy.getPolicyDescription()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = prepareStatement(connection, SQL_INSERT_POLICY_TYPE, true, values)) {
            statement.executeUpdate();
            try (ResultSet generateKeys = statement.getGeneratedKeys()) {
                if (generateKeys.next()) {
                    policy.setPolicyTypeId(generateKeys.getInt(1));
                } else {
                    throw new DAOException("Error: New policy type creation failed, no generated key obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error: New policy type creation failed.");
        }
    }

    @Override
    public PolicyType findByPrimaryKey(Integer ID) throws DAOException {
        return find(ID, SearchColumn.ID);
    }

    @Override
    public PolicyType findByTypeName(String name) throws DAOException {
        return find(name, SearchColumn.NAME);
    }

    private PolicyType find(Object value, SearchColumn searchColumn) throws DAOException, IllegalArgumentException {
        PolicyType policy = null;
        String sql = getSQLQuery(searchColumn);
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = prepareStatement(connection, sql, false, value);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                policy = getPolicyFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Error: Could not retrieve policy type information.");
        }
        return policy;
    }

    private String getSQLQuery(SearchColumn column) throws IllegalArgumentException {
        switch (column) {
            case ID:
                return SQL_FIND_BY_ID_POLICY_TYPE;
            case NAME:
                return SQL_FIND_BY_POLICY_NAME;
            default:
                throw new IllegalArgumentException("Invalid search column");
        }
    }

    private PolicyType getPolicyFromResultSet(ResultSet resultSet) throws SQLException {
        PolicyType policy = new PolicyType();
        policy.setPolicyTypeId(resultSet.getInt("policy_type_id"));
        policy.setTypeName(resultSet.getString("type_name"));
        policy.setPolicyDescription(resultSet.getString("policy_description"));
        return policy;
    }

    @Override
    public Map<Integer, PolicyType> getPoliciesMap() throws DAOException {
        Map<Integer, PolicyType> policyMap = new HashMap<>();
        setPolicyMap(policyMap);
        return policyMap;
    }

    private void setPolicyMap(Map<Integer, PolicyType> policyMap) throws DAOException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = prepareStatement(connection, SQL_FULL_TABLE, false, "policy_type")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PolicyType policy = getPolicyFromResultSet(resultSet);
                policyMap.put(policy.getPolicyTypeId(), policy);
            }
        } catch (SQLException e) {
            throw new DAOException("Error: Could not retrieve policy type table information.");
        }
    }

}
