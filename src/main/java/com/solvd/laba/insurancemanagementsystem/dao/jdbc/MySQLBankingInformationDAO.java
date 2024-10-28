package com.solvd.laba.insurancemanagementsystem.dao.jdbc;

import com.solvd.laba.insurancemanagementsystem.connection.DBConnection;
import com.solvd.laba.insurancemanagementsystem.constants.SearchColumn;
import com.solvd.laba.insurancemanagementsystem.dao.BankingInformationDAO;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.factory.DAOFactory;
import com.solvd.laba.insurancemanagementsystem.model.Addresses;
import com.solvd.laba.insurancemanagementsystem.model.BankingInformation;
import com.solvd.laba.insurancemanagementsystem.model.Members;
import com.solvd.laba.insurancemanagementsystem.services.AddressesService;
import com.solvd.laba.insurancemanagementsystem.services.MembersService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.solvd.laba.insurancemanagementsystem.constants.Constants.*;
import static com.solvd.laba.insurancemanagementsystem.utilities.DAOUtilities.prepareStatement;

public class MySQLBankingInformationDAO implements BankingInformationDAO {

    @Override
    public void create(BankingInformation bankInfo) throws DAOException {
        if (bankInfo.getBankingId() != null) {
            throw new IllegalArgumentException("Banking Information is already created. Banking Information already has ID");
        }
        Object[] values = {bankInfo.getCardNum(), bankInfo.getCardType(), bankInfo.getBillingAddress().getAddressId(), bankInfo.getBankingMember().getMemberId()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = prepareStatement(connection, SQL_INSERT_BANK, true, values)) {
            statement.executeUpdate();
            try (ResultSet generateKeys = statement.getGeneratedKeys()) {
                if (generateKeys.next()) {
                    bankInfo.setBankingId(generateKeys.getInt(1));
                } else {
                    throw new DAOException("Error: New banking information creation failed, no generated key obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error: New banking information creation failed.");
        }
    }

    @Override
    public BankingInformation findByPrimaryKey(Integer ID) throws DAOException {
        return find(ID, SearchColumn.ID);
    }

    @Override
    public BankingInformation findByMemberID(Integer ID) throws DAOException {
        return find(ID, SearchColumn.FOREIGN_KEY_ID);
    }

    @Override
    public BankingInformation findByCardNum(String cardNum) throws DAOException {
        return find(cardNum, SearchColumn.CARD);
    }

    @Override
    public Map<Integer, BankingInformation> getBankingInfoMap() throws DAOException {
        Map<Integer, BankingInformation> bankInfoMap = new HashMap<>();
        setBankInfoMap(bankInfoMap);
        return bankInfoMap;
    }

    private void setBankInfoMap(Map<Integer, BankingInformation> bankInfoMap) throws DAOException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = prepareStatement(connection, SQL_FULL_TABLE, false, "banking_information")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                BankingInformation bankInfo = getBankInfoFromResultSet(resultSet);
                bankInfoMap.put(bankInfo.getBankingId(), bankInfo);
            }
        } catch (SQLException e) {
            throw new DAOException("Error: Could not retrieve banking information table information.");
        }
    }

    private BankingInformation find(Object value, SearchColumn searchColumn) throws DAOException, IllegalArgumentException {
        BankingInformation bankInfo = null;
        String sql = getSQLQuery(searchColumn);
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = prepareStatement(connection, sql, false, value);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                bankInfo = getBankInfoFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Error: Could not retrieve banking information.");
        }
        return bankInfo;
    }

    private String getSQLQuery(SearchColumn column) throws IllegalArgumentException {
        switch (column) {
            case ID:
                return SQL_FIND_BY_ID_BANK;
            case CARD:
                return SQL_FIND_BY_CARD_BANK;
            case FOREIGN_KEY_ID:
                return SQL_FIND_BY_MEMBER_ID_BANK;
            default:
                throw new IllegalArgumentException("Invalid search column");
        }
    }

    private BankingInformation getBankInfoFromResultSet(ResultSet resultSet) throws SQLException {
        BankingInformation bankInfo = new BankingInformation();
        bankInfo.setBankingId(resultSet.getInt("banking_id"));
        bankInfo.setCardNum(resultSet.getString("card_number"));
        bankInfo.setCardType(resultSet.getString("card_type"));
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(MYSQL);
        AddressesService addressesService = new AddressesService(mySQLFactory);
        Addresses billingAddress = addressesService.addressesById(resultSet.getInt("primary_address_id"));
        MembersService membersService = new MembersService(mySQLFactory);
        Members bankingMember = membersService.membersById(resultSet.getInt("banking_member_id"));
        bankInfo.setBillingAddress(billingAddress);
        bankInfo.setBankingMember(bankingMember);
        return bankInfo;
    }

}
