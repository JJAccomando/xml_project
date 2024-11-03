package com.solvd.laba.insurancemanagementsystem.dao;

import com.solvd.laba.insurancemanagementsystem.exceptions.DAOException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface GenericDAO <T, V> {

    void create(T element) throws DAOException;

    T findByPrimaryKey(V value) throws DAOException;

    Map<V, T> getMap() throws DAOException;

}

