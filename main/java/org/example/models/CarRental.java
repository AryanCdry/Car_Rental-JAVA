package org.example.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRental {
    static String DB_URL = "jdbc:sqlite:car.db";

    public CarRental() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String createTable = "CREATE TABLE IF NOT EXISTS cars (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "brand TEXT," +
                "price INTEGER," +
                "available BOOLEAN)";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()) {

            statement.execute(createTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM cars")) {

            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setPrice(resultSet.getInt("price"));
                car.setAvailable(resultSet.getBoolean("available"));

                cars.add(car);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }
    public double rentCar(int carId, String customerName) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement rentStatement = connection.prepareStatement(
                     "INSERT INTO rentals (car_id, customer_name) VALUES (?, ?)");
             PreparedStatement updateStatement = connection.prepareStatement(
                     "UPDATE cars SET available = false WHERE id = ?")) {

            rentStatement.setInt(1, carId);
            rentStatement.setString(2, customerName);
            rentStatement.executeUpdate();

            updateStatement.setInt(1, carId);
            updateStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void returnCar(int carId) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement returnStatement = connection.prepareStatement(
                     "DELETE FROM rentals WHERE car_id = ?");
             PreparedStatement updateStatement = connection.prepareStatement(
                     "UPDATE cars SET available = true WHERE id = ?")) {

            returnStatement.setInt(1, carId);
            returnStatement.executeUpdate();

            updateStatement.setInt(1, carId);
            updateStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCar(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM cars WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Car deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCar(String brand, int price, boolean available) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cars (brand, price, available) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, brand);
            preparedStatement.setInt(2, price);
            preparedStatement.setBoolean(3, available);
            preparedStatement.executeUpdate();

            System.out.println("Car added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
