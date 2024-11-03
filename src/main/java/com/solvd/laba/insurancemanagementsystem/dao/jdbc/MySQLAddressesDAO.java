package com.solvd.laba.insurancemanagementsystem.dao.jdbc;

import com.solvd.laba.insurancemanagementsystem.connection.DBConnection;
import com.solvd.laba.insurancemanagementsystem.dao.AddressesDAO;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.model.Addresses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.solvd.laba.insurancemanagementsystem.constants.Constants.*;
import static com.solvd.laba.insurancemanagementsystem.utilities.DAOUtilities.prepareStatement;

public class MySQLAddressesDAO implements AddressesDAO {

    @Override
    public Map<Integer, Addresses> getMap() throws DAOException {
        Map<Integer, Addresses> addressesMap = new HashMap<>();
        setAddressesMap(addressesMap);
        return addressesMap;
    }

    private void setAddressesMap(Map<Integer, Addresses> addressesMap) throws DAOException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = prepareStatement(connection, SQL_FULL_TABLE_ADDRESSES, false)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Addresses address = getAddressFromResultSet(resultSet);
                addressesMap.put(address.getAddressId(), address);
            }
        } catch (SQLException e) {
            throw new DAOException("Error: Could not retrieve addresses table information.");
        }
    }

    @Override
    public void create(Addresses address) throws DAOException {
        if (address.getAddressId() != null) {
            throw new IllegalArgumentException("Address is already created. Address already has ID");
        }
        Object[] values = {
                address.getBuildingNum(), address.getUnitNum(), address.getStreet(),
                address.getCity(), address.getState(), address.getCountry(), address.getPostalCode()
        };
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = prepareStatement(connection, SQL_INSERT_ADDRESSES, true, values)) {
            statement.executeUpdate();
            try (ResultSet generateKeys = statement.getGeneratedKeys()) {
                if (generateKeys.next()) {
                    address.setAddressId(generateKeys.getInt(1));
                } else {
                    throw new DAOException("Error: New address creation failed, no generated key obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error: New address creation failed.");
        }
    }

    @Override
    public Addresses findByPrimaryKey(Integer Id) throws DAOException {
        Addresses address = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = prepareStatement(connection, SQL_FIND_BY_ID_ADDRESSES, false, Id);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                address = getAddressFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Error: Could not retrieve address information.");
        }
        return address;
    }

    private Addresses getAddressFromResultSet(ResultSet resultSet) throws SQLException {
        Addresses address = new Addresses();
        address.setAddressId(resultSet.getInt("address_id"));
        address.setBuildingNum(resultSet.getInt("building_number"));
        address.setUnitNum(resultSet.getString("unit_number"));
        address.setStreet(resultSet.getString("street"));
        address.setCity(resultSet.getString("city"));
        address.setState(resultSet.getString("state"));
        address.setCountry(resultSet.getString("country"));
        address.setPostalCode(resultSet.getString("postal_code"));
        return address;
    }

}
