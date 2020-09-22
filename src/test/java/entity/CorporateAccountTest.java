package entity;

import exception.InsufficientBalanceException;
import exception.NegativeAmountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CorporateAccountTest {

    @Test
    public void shouldInvokeWithdrawal() throws NegativeAmountException, InsufficientBalanceException {
        //given

        CorporateAccount account= new CorporateAccount();

        double testPayment = 1000d;
        double testWithdrawal = 90d;

        account.payment(testPayment);
        //when
        account.withdrawal(testWithdrawal);


        //then
        Assertions.assertEquals(BigDecimal.valueOf(testPayment-testWithdrawal-account.getCorporateFee()), account.getBalance());

    }

}