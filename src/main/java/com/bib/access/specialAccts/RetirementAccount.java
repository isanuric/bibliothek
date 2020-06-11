package com.bib.me.specialAccts;

import com.bib.me.accts.SavingAccount;


class RetirementAccount extends SavingAccount {

    private String maturityDate;

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public static void main(String[] args) {

        // Account a = new Account(); //will not compile RetirementAccount ra = new RetirementAccount(); //valid ra.balance = 100.0; //valid ra.setAccountNumber(10); //valid ra.setInterestRate(7.0); //valid
        RetirementAccount ra = new RetirementAccount(); //valid ra.balance = 100.0; //valid ra.setAccountNumber(10); //valid ra.setInterestRate(7.0); //valid

        // a protected member of a class is visible to another class
        // that belongs to a different package if that class is a subclass of this class.
        ra.savingNr = 10;

        ra.balance = 100.0;      //valid
        ra.setAccountNumber(10); //valid
        ra.setInterestRate(7.0); //valid




        SavingAccount sa = new SavingAccount(); //valid
        // sa.savingNr = 10;      //will not compile
        // sa.balance = 10.0;     //will not compile
        // ra.accountNumber = 10; //will not compile
        // ra.interestRate = 7.0; //will not compile


    }
}
