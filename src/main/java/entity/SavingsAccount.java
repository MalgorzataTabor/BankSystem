package entity;

import exception.NegativeAmountException;

import java.math.BigDecimal;

public class SavingsAccount extends Account {

    private double savingFactor =1.01;

    public SavingsAccount() {
        super();
        type =AccountType.SAVINGS;

    }

    public SavingsAccount(String number, BigDecimal balance) {
        super(number, balance);
        type =AccountType.SAVINGS;

    }

    @Override
    public void payment(double amount) throws NegativeAmountException {
        super.payment(amount * savingFactor);
    }

    public double getSavingFactor() {
        return savingFactor;
    }
}
