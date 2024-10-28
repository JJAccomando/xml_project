package com.solvd.laba.insurancemanagementsystem.dao.jdbc;

import com.solvd.laba.insurancemanagementsystem.connection.DBConnection;
import com.solvd.laba.insurancemanagementsystem.constants.SearchColumn;
import com.solvd.laba.insurancemanagementsystem.dao.MembersDAO;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.factory.DAOFactory;
import com.solvd.laba.insurancemanagementsystem.model.Addresses;
import com.solvd.laba.insurancemanagementsystem.model.Members;
import com.solvd.laba.insurancemanagementsystem.services.AddressesService;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static com.solvd.laba.insurancemanagementsystem.constants.Constants.*;
import static com.solvd.laba.insurancemanagementsystem.utilities.DAOUtilities.prepareStatement;

public class MySQLMembersDAO implements MembersDAO {

    @Override
    public Map<Integer, Members> getMembersMap() throws DAOException {
        Map<Integer, Members> membersMap = new HashMap<>();
        setMembersMap(membersMap);
        return membersMap;
    }

    @Override
    public void create(Members member) throws DAOException {
        if (member.getMemberId() != null) {
            throw new IllegalArgumentException("Member is already created. Member already has ID");
        }
        Object[] values = {member.getFirstName(), member.getLastName(), member.getPhoneNum(), member.getEmail(), member.getDateOfBirth(), member.getAgeGroup().getId()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = prepareStatement(connection, SQL_INSERT_MEMBERS, true, values)) {
            statement.executeUpdate();
            try (ResultSet generateKeys = statement.getGeneratedKeys()) {
                if (generateKeys.next()) {
                    member.setId(generateKeys.getInt(1));
                } else {
                    throw new DAOException("Error: New member creation failed, no generated key obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error: New member creation failed.");
        }
    }

    private void setMembersMap(Map<Integer, Members> membersMap) throws DAOException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = prepareStatement(connection, SQL_FULL_TABLE, false, "members")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Members member = getMembersFromResultSet(resultSet);
                membersMap.put(member.getMemberId(), member);
            }
        } catch (SQLException e) {
            throw new DAOException("Error: Could not retrieve members table information.");
        }
    }

    @Override
    public Members findByPrimaryKey(Integer Id) { return find(Id, SearchColumn.ID); }

    @Override
    public Members findByEmail(String email) { return find(email, SearchColumn.EMAIL); }

    @Override
    public Members findByPhoneNum(String phoneNum) { return find(phoneNum, SearchColumn.PHONE_NUM); }

    private Members find(Object value, SearchColumn searchColumn) throws DAOException, IllegalArgumentException {
        Members member = null;
        String sql = getSQLQuery(searchColumn);
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = prepareStatement(connection, sql, false, value);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                member = getMembersFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Error: Could not retrieve member information.");
        }
        return member;
    }

    private String getSQLQuery(SearchColumn column) throws IllegalArgumentException {
        switch (column) {
            case ID:
                return SQL_FIND_BY_ID_MEMBERS;
            case EMAIL:
                return SQL_FIND_BY_EMAIL_MEMBERS;
            case PHONE_NUM:
                return SQL_FIND_BY_PHONE_MEMBERS;
            default:
                throw new IllegalArgumentException("Invalid search column");
        }
    }

    private Members getMembersFromResultSet(ResultSet resultSet) throws SQLException {
        Members member = new Members();
        member.setId(resultSet.getInt("member_id"));
        member.setFirstName(resultSet.getString("first_name"));
        member.setLastName(resultSet.getString("last_name"));
        member.setPhoneNum(resultSet.getString("phone_number"));
        member.setEmail(resultSet.getString("email"));
        member.setDateOfBirth(resultSet.getString("date_of_birth"));
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(MYSQL);
        AddressesService addressesService = new AddressesService(mySQLFactory);
        Addresses primaryAddress = addressesService.addressesById(resultSet.getInt("primary_address_id"));
        member.setAddress(primaryAddress);
        return member;
    }

}
