package com.pluralsight.dealership.dataManager;

import javax.sql.*;
import java.sql.*;
import java.util.*;

public class VehicleDAO {
    private DataSource dataSource;
    // common part of all SQL commands in this class
    private final String BASIC_SQL = """
            SELECT inventory.dealership_id, vehicles.VIN, vehicles.year, vehicles.make,
                               vehicles.model, vehicles.type, vehicles.color,
                               vehicles.mileage, vehicles.price, vehicles.SOLD
                        FROM vehicles
                        LEFT JOIN inventory ON inventory.VIN = vehicles.VIN
            """;

    public VehicleDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void getVehiclesByPrice(double min, double max) {
        String sql = BASIC_SQL + " WHERE vehicles.price BETWEEN ? AND ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setDouble(1, min);
            preparedStatement.setDouble(2, max);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                printVehicles(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void getVehiclesByMakeModel(String make, String model) {
        String sql = BASIC_SQL + " WHERE vehicles.make = ? AND vehicles.model = ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                printVehicles(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getVehiclesByYear(int min, int max) {
        String sql = BASIC_SQL + " WHERE vehicles.year BETWEEN ? AND ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                printVehicles(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getVehiclesByColor(String color) {
        String sql = BASIC_SQL + " WHERE vehicles.color = ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, color);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                printVehicles(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getVehiclesByMileage(int min, int max) {
        String sql = BASIC_SQL + " WHERE vehicles.mileage BETWEEN ? AND ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                printVehicles(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getVehiclesByType(String type) {
        String sql = BASIC_SQL + " WHERE vehicles.type = ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, type);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                printVehicles(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllVehicles() {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(BASIC_SQL + "ORDER BY dealership_id");
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            printVehicles(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addVehicle(int dealershipId, String vin, int year, String make, String model, String type, String color, int odometer, double price) {
        String vehicleSql = """
        INSERT INTO vehicles (VIN, year, make, model, type, color, mileage, price, SOLD)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        String inventorySql = """
        INSERT INTO inventory (dealership_id, VIN)
        VALUES (?, ?)
        """;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement vehicleStmt = connection.prepareStatement(vehicleSql);
                PreparedStatement inventoryStmt = connection.prepareStatement(inventorySql)
        ) {
            vehicleStmt.setString(1, vin);
            vehicleStmt.setInt(2, year);
            vehicleStmt.setString(3, make);
            vehicleStmt.setString(4, model);
            vehicleStmt.setString(5, type);
            vehicleStmt.setString(6, color);
            vehicleStmt.setInt(7, odometer);
            vehicleStmt.setDouble(8, price);
            vehicleStmt.setBoolean(9, false);

            vehicleStmt.executeUpdate();
            System.out.println("Vehicle added successfully. ");

            // Insert into inventory table
            if(dealershipId != 0){
                inventoryStmt.setInt(1, dealershipId);
                inventoryStmt.setString(2, vin);

                inventoryStmt.executeUpdate();
                System.out.println("Dealership info saved. ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add vehicle. ");
        }
    }

    public void removeVehicle(String vin) {
        String inventorySql = "DELETE FROM inventory WHERE VIN = ?";
        String vehicleSql = "DELETE FROM vehicles WHERE VIN = ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement inventoryStmt = connection.prepareStatement(inventorySql);
                PreparedStatement vehicleStmt = connection.prepareStatement(vehicleSql)
        ) {
            inventoryStmt.setString(1, vin);
            inventoryStmt.executeUpdate();

            vehicleStmt.setString(1, vin);
            int vehicleRowsUpdated = vehicleStmt.executeUpdate();

            if(vehicleRowsUpdated > 0)
                System.out.println("Vehicle successfully deleted. ");
            else
                System.out.println("Vehicle failed to be deleted. ");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to delete vehicle.");
        }
    }


    // helper method to print tables of selected vehicles
    private void printVehicles(ResultSet resultSet) throws SQLException {
        System.out.printf(
                "%-15s %-18s %-6s %-10s %-15s %-12s %-10s %-8s %-10s %-5s%n",
                "Dealership ID", "VIN", "Year", "Make", "Model", "Type", "Color", "Miles", "Price", "Sold"
        );

        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        while (resultSet.next()) {
            System.out.printf(
                    "%-15d %-18s %-6d %-10s %-15s %-12s %-10s %-8d $%-9.2f %-5s%n",
                    resultSet.getInt("dealership_id"),
                    resultSet.getString("VIN"),
                    resultSet.getInt("year"),
                    resultSet.getString("make"),
                    resultSet.getString("model"),
                    resultSet.getString("type"),
                    resultSet.getString("color"),
                    resultSet.getInt("mileage"),
                    resultSet.getBigDecimal("price"),
                    resultSet.getBoolean("SOLD") ? "Yes" : "No"
            );
        }
    }

}