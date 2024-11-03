package com.solvd.laba.insurancemanagementsystem.dao;

import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;
import com.solvd.laba.insurancemanagementsystem.model.PolicyType;

import java.util.Map;

public interface PolicyTypeDAO extends GenericDAO<PolicyType, Integer> {

     PolicyType findByTypeName(String name) throws DAOException;

}
