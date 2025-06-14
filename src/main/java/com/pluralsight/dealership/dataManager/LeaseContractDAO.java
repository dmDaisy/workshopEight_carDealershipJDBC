package com.pluralsight.dealership.dataManager;

import javax.sql.DataSource;

public class LeaseContractDAO {
    private DataSource dataSource;

    public LeaseContractDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }
}
