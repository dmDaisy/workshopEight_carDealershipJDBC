package com.pluralsight.dealership.dataManager;

import javax.sql.*;
import java.sql.*;

public class DealershipDAO {
    private DataSource dataSource;

    public DealershipDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void getAllDealerships() {
        String sql = "SELECT * FROM dealerships";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            System.out.printf("%-5s %-20s %-30s %-15s%n", "ID", "Name", "Address", "Phone");
            System.out.println("-------------------------------------------------------------------------------");

            while (resultSet.next()) {
                System.out.printf(
                        "%-5d %-20s %-30s %-15s%n",
                        resultSet.getInt("dealership_id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to fetch dealerships.");
        }
    }

    public int getMinDealershipId() {
        String sql = "SELECT MIN(dealership_id) AS min_id FROM dealerships";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if (resultSet.next()) {
                return resultSet.getInt("min_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // error
    }

    public int getMaxDealershipId() {
        String sql = "SELECT MAX(dealership_id) AS max_id FROM dealerships";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if (resultSet.next()) {
                return resultSet.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // error
    }

}
