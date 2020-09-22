package main;

import DAO.ClientDAO;
import DAO.ClientDAOFile;
import entity.Client;
import entity.CorporateAccount;
import entity.CurrentAccount;
import entity.SavingsAccount;
import exception.InsufficientBalanceException;
import exception.NegativeAmountException;

import java.io.IOException;
import java.util.Arrays;

public class ClientDAOFileMain {
    public static void main(String[] args) throws IOException, NegativeAmountException, InsufficientBalanceException {
        ClientDAO clientDAO = new ClientDAOFile();

        Client client1 = new Client("Jan", "Kowalski", "12345678912", "jan.kowalski", "password");
        Client client2 = new Client("Michał", "Nowak", "98765432198", "michal.nowak", "drowssap");
        Client client3 = new Client("Dorota", "Zal", "456123456", "dorota.zal", "doris");

        client1.getAccounts().add(new SavingsAccount());
        client1.getAccounts().add(new CurrentAccount());


        client2.getAccounts().add(new CorporateAccount());
        client2.getAccounts().add(new CurrentAccount());

        client3.getAccounts().add(new CurrentAccount());

        clientDAO.save(Arrays.asList(client1, client2, client3));

        System.out.println(clientDAO.get());
    }
}
