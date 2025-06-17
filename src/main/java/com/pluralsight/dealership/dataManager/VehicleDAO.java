package com.pluralsight.dealership.dataManager;

import com.pluralsight.dealership.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.*;
import java.sql.*;
import java.util.*;

public class VehicleDAO {
    private DataSource dataSource;

    public VehicleDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max){
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model){
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max){
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color){
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max){
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByType(String type){
        return null;
    }

    public void getAllVehicles(){
        // Create the connection and prepared statement
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             """
                        SELECT inventory.dealership_id, vehicles.VIN, vehicles.year, vehicles.make,
                               vehicles.model, vehicles.type, vehicles.color,
                               vehicles.mileage, vehicles.price, vehicles.SOLD
                        FROM inventory
                        LEFT JOIN vehicles ON inventory.VIN = vehicles.VIN;
                     """);
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                System.out.printf("%-5s %-18s %-6s %-10s %-15s %-10s %-10s %-8s %-10s %-5s%n",
                        "ID", "VIN", "Year", "Make", "Model", "Type", "Color", "Miles", "Price", "Sold");

                System.out.println("---------------------------------------------------------------------------------------------------------------");

                while (resultSet.next()) {
                    System.out.printf("%-5d %-18s %-6d %-10s %-15s %-10s %-10s %-8d $%-9.2f %-5s%n",
                            resultSet.getInt("dealership_id"),
                            resultSet.getString("VIN"),
                            resultSet.getInt("year"),
                            resultSet.getString("make"),
                            resultSet.getString("model"),
                            resultSet.getString("type"),
                            resultSet.getString("color"),
                            resultSet.getInt("mileage"),
                            resultSet.getBigDecimal("price"),
                            resultSet.getBoolean("SOLD") ? "Yes" : "No");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
