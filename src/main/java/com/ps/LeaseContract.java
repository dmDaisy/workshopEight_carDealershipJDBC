package com.ps;

import java.time.LocalDate;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold, double price, double monthlyPayment) {
        super(date, customerName, customerEmail, vehicleSold, price, monthlyPayment);
        double vehiclePrice = this.getVehicleSold().getPrice();
        expectedEndingValue = vehiclePrice * 0.5;
        leaseFee = vehiclePrice * 0.07;
    }

    @Override
    public double getTotalPrice() {
        return 0;
    }

    @Override
    public double getMonthlyPayment() {
        return 0;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }
}
