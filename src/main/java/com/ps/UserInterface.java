package com.ps;

import java.time.LocalDate;
import java.util.*;

public class UserInterface {
    private Dealership dealership;
    private final Scanner scanner = new Scanner(System.in);

    private void init(){
        dealership = DealershipFileManager.getDealership();
    }

    public UserInterface(){
        init();
    }

    public void display(){

        int mainMenuCommand = -1;

        do{
            System.out.println("\nWelcome to the dealership program");
            System.out.println("1. Get by price");
            System.out.println("2. Get by make/model");
            System.out.println("3. Get by year");
            System.out.println("4. Get by color");
            System.out.println("5. Get by mileage");
            System.out.println("6. Get by type");
            System.out.println("7. Get all");
            System.out.println("8. Add vehicle");
            System.out.println("9. Remove vehicle");
            System.out.println("10. Buy/Lease a vehicle");
            System.out.println("0. Exit");

            System.out.print("Command: ");
            mainMenuCommand = getUserInt();

            switch(mainMenuCommand){
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 10:
                    processBuyOrLeaseRequest();
                    break;
                case 0:
                    System.out.println("Saving inventory to cvs file...");
                    DealershipFileManager.saveDealership(dealership);
                    System.out.println("Done. ");
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Command not found, try again");
            }
        } while(mainMenuCommand != 0);
    }


    private void processGetByPriceRequest(){
        // TODO: Ask the user for a starting price and ending price
        System.out.println("--------Display vehicles by price--------");
        System.out.print("Min: ");
        double min = scanner.nextDouble();

        System.out.print("Max: ");
        double max = scanner.nextDouble();

        // ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByPrice(startingPrice, endingPrice);
        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByPrice(min, max);

        // Display vehicles with for loop
        displayVehicles(filteredVehicles, "price");
    }
    private void processGetByMakeModelRequest(){
        System.out.println("Enter vehicle make: ");
        String make = scanner.nextLine().trim();
        System.out.println("Enter vehicle model: ");
        String model = scanner.nextLine().trim();

        displayVehicles(dealership.vehiclesByMakeModel(make, model), "make and model");
    }
    private void processGetByYearRequest(){
        System.out.println("Enter starting year: ");
        int min = scanner.nextInt();
        scanner.nextLine(); // consumes the redundant line
        System.out.println("Enter ending year: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        displayVehicles(dealership.vehiclesByYear(min, max), "year");
    }
    private void processGetByColorRequest(){
        System.out.println("Enter color: ");
        String color = scanner.nextLine();
        displayVehicles(dealership.vehiclesByColor(color), "color");
    }
    private void processGetByMileageRequest(){
        System.out.println("Enter min mileage: ");
        int min = getUserInt();
        System.out.println("Enter max mileage: ");
        int max = getUserInt();

        displayVehicles(dealership.vehiclesByMileage(min, max), "mileage");
    }

    private void processGetByVehicleTypeRequest(){
        System.out.println("Enter type: ");
        String type = scanner.nextLine();
        displayVehicles(dealership.vehiclesByType(type), "type");
    }

    private void processGetAllVehiclesRequest(){
        displayVehicles(dealership.getAllVehicles(), "all vehicles");
    }

    private void processAddVehicleRequest(){
        System.out.println("Enter vehicle VIN: ");
        int vin = getUserInt();
        System.out.println("Enter vehicle year: ");
        int year = getUserInt();
        System.out.println("Enter vehicle make: ");
        String make = scanner.nextLine();
        System.out.println("Enter vehicle model: ");
        String model = scanner.nextLine();
        System.out.println("Enter vehicle type: ");
        String type = scanner.nextLine();
        System.out.println("Enter vehicle color: ");
        String color = scanner.nextLine();
        System.out.println("Enter vehicle mileage: ");
        int mileage = getUserInt();
        System.out.println("Enter vehicle price: ");
        double price = scanner.nextDouble();

        Vehicle v = new Vehicle(vin, year, make, model, type, color, mileage, price);
        dealership.addVehicle(v);
        System.out.println("The following vehicle is added: " +
                "\n" + v);
    }

    // removes by vin only, removes 1 vehicle with each call
    private void processRemoveVehicleRequest(){
        System.out.println("Enter vehicle VIN: ");
        int vin = getUserInt();
        System.out.println("The following vehicle is removed: ");
        dealership.removeVehicle(vin);
    }

    public void processBuyOrLeaseRequest(){
        System.out.println("Enter VIN of the vehicle you want to buy or lease: ");
        int vin = getUserInt();
        Vehicle vehicleSelected = dealership.vehicleByVin(vin);
        if(vehicleSelected == null){
            System.out.println("Invalid VIN, car not found. Going back to main menu...");
            return;
        }
        System.out.println("Enter 1 to buy, enter 2 to lease: ");
        int choice = getUserInt();
        if(choice != 1 && choice != 2){
            System.out.println("Invalid choice, going back to main menu...");
            return;
        }

        LocalDate date = LocalDate.now();
        if(choice == 2 && (date.getYear() - vehicleSelected.getYear()) >= 3){ // no cars older than 3 years
            System.out.println("Cannot lease cars over 3 years old. Going back to home page...");
            return;
        }
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        Contract contract = null;
        if(choice == 1){ // sales contract
            boolean financed = false;
            System.out.println("Do you want to pay by month? (Reply Y to confirm)");
            String s = scanner.nextLine().trim().toUpperCase();
            if(s.equalsIgnoreCase("Y"))
                financed = true;
            contract = new SalesContract(date, name, email, vehicleSelected, financed);
        }
        else if(choice == 2) // lease contract
            contract = new LeaseContract(date, name, email, vehicleSelected);

        ContractDataManager.saveContract(contract);
        System.out.println("The following contract is saved: \n" + contract);
        dealership.removeVehicle(vehicleSelected);
    }

    // helper method
    public static void displayVehicles(ArrayList<Vehicle> vehicles, String type){
        System.out.println("\nPrinting the corresponding vehicle(s) by: " + type);
        for(Vehicle vehicle: vehicles){
            System.out.println(vehicle);
        }
    }

    // helper method to get user int input and consumes redundant \n
    private int getUserInt(){
        while( ! scanner.hasNextInt()){
            System.out.println("Invalid input, enter an integer: ");
            scanner.nextLine(); // or scanner.next() ?
        }
        int result = scanner.nextInt();
        scanner.nextLine(); // consumes redundant \n, or scanner.next() ?

        return result;
    }
}
