package com.pluralsight.dealership.dataManager;

import javax.sql.DataSource;

public class SalesContractDAO {
    private DataSource dataSource;

    public SalesContractDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }
}
