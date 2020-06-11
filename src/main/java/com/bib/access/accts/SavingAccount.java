package com.bib.me.accts;


public class SavingAccount extends Account {

    double interestRate;
    protected int savingNr;

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
