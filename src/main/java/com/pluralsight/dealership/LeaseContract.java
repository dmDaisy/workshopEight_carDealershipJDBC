package com.pluralsight.dealership;

import java.time.LocalDate;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        double vehiclePrice = this.getVehicleSold().getPrice();
        expectedEndingValue = vehiclePrice * 0.5;
        leaseFee = vehiclePrice * 0.07;
        setTotalPrice(getTotalPrice());
        setMonthlyPayment(getMonthlyPayment());
    }

    @Override
    public double getTotalPrice() {
        double basePrice = getVehicleSold().getPrice();
        double expectedEndingValue = basePrice * 0.5;
        double leaseFee = basePrice * 0.07;

        return leaseFee + expectedEndingValue;
    }

    @Override
    public double getMonthlyPayment() {
        double totalPrice = getTotalPrice();
        double apr = 0.04;
        double mpr = apr / 12;
        int totalMonths = 36;

        // loan formula:
        // M = P * r / (1 - (1 + r)^-n)
        double result = (totalPrice * mpr) / (1 - Math.pow(1 + mpr, -totalMonths));

        return result;
    }

    @Override
    public String toCsvEntry() {
        return  String.format(
                "%s|%.2f|%.2f|%.2f|%.2f",
                super.toCsvEntry(),
                getExpectedEndingValue(),
                getLeaseFee(),
                getTotalPrice(),
                getMonthlyPayment()
        );
    }

    @Override
    public String toString() {
        return super.toString() +
                " | expectedEndingValue: " + String.format("%.2f", expectedEndingValue) +
                " | leaseFee: " + String.format("%.2f", leaseFee) +
                " | totalPrice: " + String.format("%.2f", getTotalPrice()) +
                " | monthlyPayment: " + String.format("%.2f", getMonthlyPayment());
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
