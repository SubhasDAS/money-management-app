package com.example.myapplication;

public class IncomeDetails {

    private String sourceOfIncome;
    private String amount;
    private int  incomeDetailId;
    private String dateOfAddIncome;

    public IncomeDetails(String sourceOfIncome, String amount, int incomeDetailId, String dateOfAddIncome) {
        this.sourceOfIncome = sourceOfIncome;
        this.amount = amount;
        this.incomeDetailId = incomeDetailId;
        this.dateOfAddIncome = dateOfAddIncome;
    }

    public IncomeDetails() {

    }

    public void setSourceOfIncome(String sourceOfIncome) {
        this.sourceOfIncome = sourceOfIncome;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setIncomeDetailId(int incomeDetailId) {
        this.incomeDetailId = incomeDetailId;
    }

    public void setDateOfAddIncome(String dateOfAddIncome) {
        this.dateOfAddIncome = dateOfAddIncome;
    }

    public String getSourceOfIncome() {
        return sourceOfIncome;
    }

    public String getAmount() {
        return amount;
    }

    public int getIncomeDetailId() {
        return incomeDetailId;
    }

    public String getDateOfAddIncome() {
        return dateOfAddIncome;
    }









}
