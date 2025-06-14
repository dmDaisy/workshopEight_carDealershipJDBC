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
        ArrayList<Vehicle> results = new ArrayList<>();

        // Create the connection and prepared statement
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "SELECT * FROM inventory;");
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                while (resultSet.next()) {
                    System.out.printf(
                            "dealership_id = %d, VIN = %s",
                            resultSet.getInt(1), resultSet.getString(2));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
