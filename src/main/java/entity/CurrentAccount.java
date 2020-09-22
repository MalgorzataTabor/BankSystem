package entity;

import java.math.BigDecimal;

public class CurrentAccount extends Account {

    public CurrentAccount() {
        super();
        type= AccountType.CURRENT;
    }

    public CurrentAccount(String number, BigDecimal balance) {
        super(number, balance);
        type=AccountType.CURRENT;
    }
}
