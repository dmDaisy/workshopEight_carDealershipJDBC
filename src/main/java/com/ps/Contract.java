package com.ps;

import java.time.LocalDate;

public abstract class Contract {
    private LocalDate date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
    private double totalPrice;
    private double monthlyPayment;

    public Contract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "date=" + date +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", vehicleSold=" + vehicleSold +
                ", price=" + totalPrice +
                ", monthlyPayment=" + monthlyPayment +
                '}';
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public abstract double getTotalPrice();

    public void setTotalPrice(double totalPrice){
        this.totalPrice = totalPrice;
    }

    public abstract double getMonthlyPayment();

    public void setMonthlyPayment(double monthlyPayment){
        this.monthlyPayment = monthlyPayment;
    }

    public String toCsvEntry(){
        String firstPart = String.format("%s|%s|%s|%.2f|",
            date.toString(),
            customerName,
            customerEmail,
            totalPrice
        );
        String secondPart = vehicleSold.toCsvEntry();
        return firstPart + secondPart;
    }
}
