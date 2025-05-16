package com.ps;

import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;

    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public Vehicle vehicleByVin(int vin){
        for(Vehicle vehicle : inventory){
            if(vehicle.getVin() == vin)
                return vehicle;
        }
        return null;
    }

    public ArrayList<Vehicle> vehiclesByPrice(double min, double max){
        ArrayList<Vehicle> list = new ArrayList<>();
        for(Vehicle v : inventory){
            double price = v.getPrice();
            if(price >= min && price <= max)
                list.add(v);
        }
        return list;
    }

    public ArrayList<Vehicle> vehiclesByMakeModel(String make, String model){
        ArrayList<Vehicle> list = new ArrayList<>();
        for(Vehicle v : inventory){
            if(v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model))
                list.add(v);
        }
        return list;
    }

    public ArrayList<Vehicle> vehiclesByYear(int min, int max){
        ArrayList<Vehicle> list = new ArrayList<>();
        for(Vehicle v : inventory){
            double year = v.getYear();
            if(year >= min && year <= max)
                list.add(v);
        }
        return list;
    }

    public ArrayList<Vehicle> vehiclesByColor(String color){
        ArrayList<Vehicle> list = new ArrayList<>();
        for(Vehicle v : inventory){
            if(v.getColor().equalsIgnoreCase(color))
                list.add(v);
        }
        return list;
    }

    public ArrayList<Vehicle> vehiclesByMileage(int min, int max){
        ArrayList<Vehicle> list = new ArrayList<>();
        for(Vehicle v : inventory){
            int mileage = v.getOdometer();
            if(mileage >= min && mileage <= max)
                list.add(v);
        }
        return list;
    }

    public ArrayList<Vehicle> vehiclesByType(String type){
        ArrayList<Vehicle> list = new ArrayList<>();
        for(Vehicle v : inventory){
            if(v.getVehicleType().equalsIgnoreCase(type))
                list.add(v);
        }
        return list;
    }

    public ArrayList<Vehicle> getAllVehicles(){
        return inventory;
    }

    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }

    public void removeVehicle(int vin){
        for(Vehicle v : inventory)
            if(v.getVin() == vin){
                System.out.println(v); // print info of removed vehicle
                inventory.remove(v);
                break;
            }
    }

    public void removeVehicle(Vehicle vehicle){
        if( ! inventory.remove(vehicle))
            System.out.println("Error removing vehicle: vehicle not removed. ");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}