package com.example.myapplication;

public class SavingDetails {
    private String date;
    private int expense;
    private int income;
    private int saving;

    public SavingDetails(String date, int expense, int income) {
        this.date = date;
        this.expense = expense;
        this.income = income;
        saving = this.income -this.expense;
    }
    public SavingDetails() {

    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getSaving() {
        return saving;
    }

    public void setSaving(int saving) {
        this.saving = saving;
    }
}