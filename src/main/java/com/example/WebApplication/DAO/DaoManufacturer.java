package com.example.WebApplication.DAO;

import com.example.WebApplication.Models.Manufacturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoManufacturer implements ManufacturerDao {

    private String jdbcURL = "jdbc:mysql://localhost:3306/cardetailsdb?useSSL=false&allowPublicKeyRetrieval=true";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_MANUFACTURER_SQL = "INSERT INTO manufacturers" + "  (id, name) VALUES " +
            " (?, ?);";

    private static final String SELECT_MANUFACTURER_BY_ID = "select id,name from manufacturers where id =?;";
    private static final String SELECT_ALL_MANUFACTURERS = "select * from manufacturers;";
    private static final String DELETE_MANUFACTURER_SQL = "delete from manufacturers where id = ?;";
    private static final String UPDATE_MANUFACTURER_SQL = "update manufacturers set name = ? where id = ?;";

    public DaoManufacturer() {
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
    public Manufacturer find(int id) throws SQLException {

        Manufacturer manufacturer = null;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MANUFACTURER_BY_ID);
        preparedStatement.setInt(1, id);
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name");
            manufacturer = new Manufacturer(id, name);
        }
        return manufacturer;
    }

    @Override
    public List<Manufacturer> findAll() throws NullPointerException {

        List<Manufacturer> listManufacturers = new ArrayList<>();
        try (Connection myconnection = getConnection();
             PreparedStatement preparedStatement = myconnection.prepareStatement(SELECT_ALL_MANUFACTURERS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Manufacturer manufacturer = new Manufacturer(id, name);
                listManufacturers.add(manufacturer);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return listManufacturers;
    }

    @Override
    public void insert(Manufacturer manufacturer) throws SQLException {

        System.out.println(INSERT_MANUFACTURER_SQL);
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MANUFACTURER_SQL);
        preparedStatement.setInt(1, manufacturer.getId());
        preparedStatement.setString(2, manufacturer.getName());
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
    }

    @Override
    public boolean update(Manufacturer manufacturer) throws SQLException {

        boolean rowUpdated;
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_MANUFACTURER_SQL);
        statement.setInt(2, manufacturer.getId());
        statement.setString(1, manufacturer.getName());

        rowUpdated = statement.executeUpdate() > 0;
        return rowUpdated;
    }

    @Override
    public boolean delete(int id) throws SQLException {

        boolean rowDeleted = false;

        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_MANUFACTURER_SQL);
        statement.setInt(1, id);
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }
}
