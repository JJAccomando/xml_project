package com.solvd.laba.insurancemanagementsystem.services;

import com.solvd.laba.insurancemanagementsystem.dao.MembersDAO;
import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.factory.DAOFactory;
import com.solvd.laba.insurancemanagementsystem.model.Members;

import java.util.Map;

public class MembersService {

    private final MembersDAO membersDAO;

    public MembersService(DAOFactory daoFactory) throws DAOException {
        this.membersDAO = daoFactory.getMembersDAO();
    }

    public void createMembers(Members member) throws DAOException {
        try {
            membersDAO.create(member);
        } catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public Members membersById(Integer id) throws DAOException {
        try {
            return membersDAO.findByPrimaryKey(id);
        } catch (IllegalArgumentException | DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public Members membersByEmail(String email) throws DAOException {
        try {
            return membersDAO.findByEmail(email);
        } catch (IllegalArgumentException | DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public Members membersByPhoneNum(String phoneNum) throws DAOException {
        try {
            return membersDAO.findByPhoneNum(phoneNum);
        } catch (IllegalArgumentException | DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public Map<Integer, Members> getAllMembers() throws DAOException {
        try {
            return membersDAO.getMap();
        } catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

}
