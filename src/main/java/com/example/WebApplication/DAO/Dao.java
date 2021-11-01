package com.example.WebApplication.DAO;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    T find(int id) throws SQLException;

    List<T> findAll() throws SQLException;

    void insert (T o) throws SQLException;

    boolean update (T o) throws SQLException;

    boolean delete (int o) throws SQLException;

}
