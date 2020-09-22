package main;

import DAO.ClientDAO;
import DAO.ClientDAOFile;
import entity.Client;
import exception.InsufficientBalanceException;
import exception.NegativeAmountException;

import java.io.IOException;
import java.util.List;

public class ClientDAOFileMain2 {
    public static void main(String[] args) throws IOException, InsufficientBalanceException, NegativeAmountException {
        ClientDAO clientDAO = new ClientDAOFile();

        List<Client> clients = clientDAO.get();

        clients.get(0).getAccounts().get(0).payment(3000);
        clients.get(0).getAccounts().get(0).transfer(clients.get(1).getAccounts().get(0),500);

        System.out.println(clients);

        clientDAO.save(clients);

    }
}
