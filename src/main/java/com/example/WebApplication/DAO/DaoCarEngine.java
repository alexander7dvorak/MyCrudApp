package com.example.WebApplication.DAO;

import com.example.WebApplication.Models.CarEngine;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoCarEngine implements CarEngineDao {

    private String jdbcURL = "jdbc:mysql://localhost:3306/cardetailsdb?useSSL=false&allowPublicKeyRetrieval=true";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_ENGINE_SQL = "INSERT INTO engines" + "  (id, manufacturer_id, name) VALUES " +
            " (?, ?, ?);";

    private static final String SELECT_ENGINE_BY_ID = "select id,manufacturer_id,name from engines where id =?;";
    private static final String SELECT_ALL_ENGINES = "select * from engines;";
    private static final String DELETE_ENGINE_SQL = "delete from engines where id = ?;";
    private static final String UPDATE_ENGINE_SQL = "update engines set manufacturer_id =?, name = ? where id = ?;";

    public DaoCarEngine() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public CarEngine find(int id) throws SQLException {

        CarEngine carEngine = null;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ENGINE_BY_ID);
        preparedStatement.setInt(1, id);
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name");
            int manufacturerId = rs.getInt("manufacturer_id");
            carEngine = new CarEngine(id, manufacturerId, name);
        }
        return carEngine;
    }

    @Override
    public List<CarEngine> findAll() throws NullPointerException {

        List<CarEngine> listEngines = new ArrayList<>();
        Connection myConnection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            myConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            try {
                PreparedStatement preparedStatement = myConnection.prepareStatement(SELECT_ALL_ENGINES);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();


                while (rs.next()) {
                    int id = rs.getInt("id");
                    int manufacturerId = rs.getInt("manufacturer_id");
                    String name = rs.getString("name");
                    CarEngine carEngine = new CarEngine(id, manufacturerId, name);
                    listEngines.add(carEngine);
                    System.out.println(carEngine.getName());
                }

            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return listEngines;
    }

    @Override
    public void insert(CarEngine carEngine) throws SQLException {

        System.out.println(INSERT_ENGINE_SQL);
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ENGINE_SQL);
        preparedStatement.setInt(1, carEngine.getId());
        preparedStatement.setInt(2, carEngine.getManufacturerId());
        preparedStatement.setString(3, carEngine.getName());
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
    }

    @Override
    public boolean update(CarEngine carEngine) throws SQLException {

        boolean rowUpdated;
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_ENGINE_SQL);
        statement.setInt(3, carEngine.getId());
        statement.setInt(1, carEngine.getManufacturerId());
        statement.setString(2, carEngine.getName());

        rowUpdated = statement.executeUpdate() > 0;
        return rowUpdated;
    }

    @Override
    public boolean delete(int id) throws SQLException {

        boolean rowDeleted = false;

        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_ENGINE_SQL);
        statement.setInt(1, id);
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }
}
