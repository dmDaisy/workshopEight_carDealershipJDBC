package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ContractDataManager {
    private static ArrayList<Contract> inventory = new ArrayList<>();
    private static final String FILE_NAME = "contracts.csv";

    // save contract to both local inventory and csv file
    public static void saveContract(Contract contract){
        if(contract == null)
            return;
        inventory.add(contract);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME, true));
            String csvEntry = contract.toCsvEntry();
            bufferedWriter.write(csvEntry);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // extract contract data from csv file and save them to local inventory
    public static ArrayList<Contract> getContracts(int num){
        return null;
    }
}
