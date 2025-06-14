package com.pluralsight.dealership;

import java.time.LocalDate;

public class SalesContract extends Contract{
    private  double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean financed;

    private final double PRICE_LIMIT = 10000;

    public SalesContract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold, boolean financed) {
        super(date, customerName, customerEmail, vehicleSold);
        double basePrice = getVehicleSold().getPrice();
        salesTaxAmount = basePrice * 0.05;
        recordingFee = 100;
        processingFee = basePrice >= PRICE_LIMIT ? 495 : 295;
        this.financed = financed;
        setTotalPrice(getTotalPrice());
        if(financed)
            setMonthlyPayment(getMonthlyPayment());
        else
            setMonthlyPayment(0);
    }

    @Override
    public double getTotalPrice(){
        double basePrice = getVehicleSold().getPrice();
        return basePrice + getSalesTaxAmount() + getRecordingFee() + getProcessingFee();
    }


    @Override
    public double getMonthlyPayment() {
        if(!financed)
            return 0;
        else{
            double totalPrice = getTotalPrice();
            int totalMonths = totalPrice >= PRICE_LIMIT ? 48 : 24;
            double apr = totalPrice >= PRICE_LIMIT ? 0.0425 : 0.0525;
            double mpr = apr / 12;

            // loan formula:
            // M = P * r / (1 - (1 + r)^-n)
            double result = (totalPrice * mpr) / (1 - Math.pow(1 + mpr, -totalMonths));
            return result;
        }
    }

    @Override
    public String toCsvEntry() {
        return  String.format(
                "%s|%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                super.toCsvEntry(),
                getSalesTaxAmount(),
                getRecordingFee(),
                getProcessingFee(),
                getTotalPrice(),
                isFinanced() ? "YES" : "NO",
                getMonthlyPayment()
        );
    }

    @Override
    public String toString() {
        return super.toString() +
                " | salesTax: " + String.format("%.2f", salesTaxAmount) +
                " | recordingFee: " + String.format("%.2f", recordingFee) +
                " | processingFee: " + String.format("%.2f", processingFee) +
                " | totalPrice: " + String.format("%.2f", getTotalPrice()) +
                " | financed: " + (financed ? "YES" : "NO") +
                " | monthlyPayment: " + String.format("%.2f", getMonthlyPayment());
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinanced() {
        return financed;
    }

    public void setfinanced(boolean financed) {
        this.financed = financed;
    }
}
