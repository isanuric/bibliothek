package com.bib.me.accts;


public class CheckingAccount extends Account {

    private double odLimit;

    public double getOdLimit() {
        return odLimit;
    }

    public void setOdLimit(double odLimit) {
        this.odLimit = odLimit;
    }
}