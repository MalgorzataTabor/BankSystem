package DAO;

import entity.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOFile implements ClientDAO {


    public static final String FILE_NAME = "clients.txt";
    public static final String CLIENT = "CLIENT";
    public static final char COMMA = ',';

    @Override
    public void save(List<Client> clients) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));

        for (Client client : clients) {
            bw.write(getClientLine(client));
            bw.newLine();
            for (Account account : client.getAccounts()) {
                bw.write(getAccountLine(account));
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();

    }

    @Override
    public List<Client> get() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        List<Client> clients = new ArrayList<>();
        Client client = null;

        String line;

        while ((line = br.readLine()) != null) {
            String[] words = line.split("" + COMMA);
            Account account = null;

            if (words[0].equals(CLIENT)) {
                client = new Client(words[1], words[2], words[3], words[4], words[5]);

                clients.add(client);
            } else {
                AccountType type = AccountType.valueOf(words[0]);
                double balance = Double.parseDouble(words[1]);

                switch (type) {

                    case CURRENT:
                        account = new CurrentAccount(words[2], BigDecimal.valueOf(balance));
                        break;

                    case SAVINGS:
                        account = new SavingsAccount(words[2], BigDecimal.valueOf(balance));
                        break;
                    case CORPORATE:
                        account = new CorporateAccount(words[2], BigDecimal.valueOf(balance));
                        break;

                    case CURRENCY:
                        break;
                }
                client.getAccounts().add(account);
            }

        }

        return clients;
    }

    private String getClientLine(Client client) {


        StringBuilder sb = new StringBuilder();
        sb.append(CLIENT)
                .append(COMMA)
                .append(client.getName())
                .append(COMMA)
                .append(client.getSurname())
                .append(COMMA)
                .append(client.getPesel())
                .append(COMMA)
                .append(client.getLogin())
                .append(COMMA)
                .append(client.getPassword());

        return sb.toString();

    }

    private String getAccountLine(Account account) {

        return new StringBuilder()
                .append(account.getType().toString())
                .append(COMMA)
                .append(account.getBalance().toString())
                .append(COMMA)
                .append(account.getNumber())
                .append(COMMA)
                .toString();
    }

}
