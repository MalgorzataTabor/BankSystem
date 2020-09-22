package entity;

import exception.InsufficientBalanceException;
import exception.NegativeAmountException;

import java.math.BigDecimal;

public class Account {

    protected String number;
    protected BigDecimal balance;
    protected AccountType type;

    public Account() {
        number = "" + System.nanoTime();
        balance = BigDecimal.ZERO;
    }

    public Account(String number, BigDecimal balance) {
        this.number = number;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public void payment(double amount) throws NegativeAmountException {

        if (amount <= 0) {
            throw new NegativeAmountException("Amount cannot be negative or zero");
        }

        balance = balance.add(BigDecimal.valueOf(amount));
    }

    public void withdrawal (double amount) throws NegativeAmountException, InsufficientBalanceException {

        if (amount <= 0) {
            throw new NegativeAmountException("Amount cannot be negative or zero");
        }

        if (amount > balance.doubleValue()){
            throw new InsufficientBalanceException("Not enough resources to complete the transaction");
        }

        balance = balance.subtract(BigDecimal.valueOf(amount));

    }

    public void transfer (Account targetAccount, double amount) throws NegativeAmountException, InsufficientBalanceException {

        withdrawal(amount);
        targetAccount.payment(amount);

    }

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                ", type=" + type +
                '}';
    }
}


