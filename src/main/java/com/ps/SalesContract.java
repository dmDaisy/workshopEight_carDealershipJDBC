package com.ps;

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
            double rate = totalPrice >= PRICE_LIMIT ? 0.0425 : 0.0525;
            return totalPrice * rate;
        }
    }

    @Override
    public String toCsvEntry() {
        return "";
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

    public boolean isfinanced() {
        return financed;
    }

    public void setfinanced(boolean financed) {
        this.financed = financed;
    }
}
