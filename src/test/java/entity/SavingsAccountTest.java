package entity;

import exception.NegativeAmountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest {

    @Test
    public void shouldInvokePayment() throws NegativeAmountException {
        //given

        SavingsAccount account= new SavingsAccount();

        double testPayment = 1000d;

        //when

        account.payment(testPayment);
        //then

        Assertions.assertEquals(BigDecimal.valueOf(testPayment * account.getSavingFactor()), account.balance);



    }

}