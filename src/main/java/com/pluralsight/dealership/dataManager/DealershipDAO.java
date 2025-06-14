package com.pluralsight.dealership.dataManager;

import com.pluralsight.dealership.models.Dealership;

import javax.sql.*;
import java.util.*;

public class DealershipDAO {
    private DataSource dataSource;

    public DealershipDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public ArrayList<Dealership> getAllDealerships(){
        return null;
    }

}
