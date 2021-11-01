package com.example.WebApplication.DAO;

import com.example.WebApplication.Models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoCar implements CarDao {

    private String jdbcURL = "jdbc:mysql://localhost:3306/cardetailsdb?useSSL=false&allowPublicKeyRetrieval=true";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_CAR_SQL = "INSERT INTO cars" + "  (id, manufacturer_id, engine_id, name) VALUES " +
            " (?, ?, ?, ?);";

    private static final String SELECT_CAR_BY_ID = "select id,manufacturer_id,engine_id,name from cars where id =?;";
    private static final String SELECT_ALL_CARS = "select * from cars";
    private static final String DELETE_CAR_SQL = "delete from cars where id = ?;";
    private static final String UPDATE_CAR_SQL = "update cars set manufacturer_id = ?, engine_id = ?, name = ? where id = ?;";

    public DaoCar() {
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
    public Car find(int id) throws SQLException {

        Car car = null;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CAR_BY_ID);
        preparedStatement.setInt(1, id);
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name");
            int manufacturerId = rs.getInt("manufacturer_id");
            int engineId = rs.getInt("engine_id");
            car = new Car(id, manufacturerId, engineId, name);
        }
        return car;
    }

    @Override
    public List<Car> findAll() throws NullPointerException {

        List<Car> listCars = new ArrayList<>();
        Connection myconnection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            myconnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement preparedStatement = myconnection.prepareStatement(SELECT_ALL_CARS);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int manufacturerId = rs.getInt("manufacturer_id");
                int engineId = rs.getInt("engine_id");
                String name = rs.getString("name");
                Car car = new Car(id, manufacturerId, engineId, name);
                listCars.add(car);
            }
        } catch (SQLException | NullPointerException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return listCars;
    }

    @Override
    public void insert(Car car) throws SQLException {

        System.out.println(INSERT_CAR_SQL);
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CAR_SQL);
        preparedStatement.setInt(1, car.getId());
        preparedStatement.setInt(2, car.getManufacturerId());
        preparedStatement.setInt(3, car.getEngineId());
        preparedStatement.setString(4, car.getName());
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
    }

    @Override
    public boolean update(Car car) throws SQLException {

        boolean rowUpdated;
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_CAR_SQL);
        statement.setInt(4, car.getId());
        statement.setInt(1, car.getManufacturerId());
        statement.setInt(2, car.getEngineId());
        statement.setString(3, car.getName());

        rowUpdated = statement.executeUpdate() > 0;
        return rowUpdated;
    }

    @Override
    public boolean delete(int id) throws SQLException {

        boolean rowDeleted = false;

        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_CAR_SQL);
        statement.setInt(1, id);
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }
}
