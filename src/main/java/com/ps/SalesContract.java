package com.ps;

import java.time.LocalDate;

public class SalesContract extends Contract{
    private final double salesTaxAmount = 0.05;
    private final double recordingFee = 100;
    private double processingFee;
    private boolean doFinance;

    public SalesContract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold, double totalPrice, double monthlyPayment, boolean doFinance) {
        super(date, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        processingFee = this.getTotalPrice() >= 10000 ? 495 : 295;
        this.doFinance = doFinance;
    }

    @Override
    public double getTotalPrice() {
        return 0;
    }

    @Override
    public double getMonthlyPayment() {
        if(!doFinance)
            return 0;
        return 0;
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

    public boolean isDoFinance() {
        return doFinance;
    }

    public void setDoFinance(boolean doFinance) {
        this.doFinance = doFinance;
    }
}
