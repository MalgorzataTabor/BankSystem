package entity;

import exception.NegativeAmountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CurrentAccountTest {

    @Test
    public void shouldInvokeDeposit() throws NegativeAmountException{

        //given

        CurrentAccount account= new CurrentAccount();
        double testDeposit = 1000d;
        //when
        account.payment(testDeposit);
        //then
        Assertions.assertEquals(BigDecimal.valueOf(testDeposit), account.getBalance());
    }

}