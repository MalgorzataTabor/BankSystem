package view;

import entity.Account;
import entity.Client;
import exception.IndexValidationException;
import exception.InsufficientBalanceException;
import exception.InvalidAccountNumberException;
import exception.NegativeAmountException;
import service.BankService;
import service.LoginService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TextView {
    public static void main(String[] args) throws IndexValidationException, InsufficientBalanceException, NegativeAmountException {

        Scanner scanner = new Scanner(System.in);

        LoginService loginService;
        BankService bankService = null;

        try {
            bankService = new BankService();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(0);
        }

        loginService = new LoginService(bankService.get());

        Client client;

        System.out.println("Podaj login: ");
        String login = scanner.next();

        System.out.println("Podaj password: ");
        String password = scanner.next();

        if (loginService.isAuthenticated(login, password)) {
            client = loginService.getClientByLoginAndPassword(login, password);
            System.out.println("Logowanie udane");

            do {
                System.out.println("Wybierz operację którą chcesz wykonać:");
                System.out.println("0. Zakończ");
                System.out.println("1. Wyświetl wszystkie konta");
                System.out.println("2. Dokonaj wpłaty");
                System.out.println("3. Dokonaj wypłaty");
                System.out.println("4. Dokonaj przelewu");


                int choice = scanner.nextInt();

                switch (choice) {

                    case 0: {
                        try {
                            bankService.save();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Dziękujemy za skorzystanie z naszych usług.");
                        System.exit(0);

                    }
                    case 1: {

                        for (Account account : client.getAccounts()) {
                            System.out.println("Typ: " + account.getType() + " numer: " + account.getNumber() + " stan: " + account.getBalance() + " zł.");

                        }
                    }
                    break;
                    case 2: {
                        List<Account> accounts = client.getAccounts();
                        System.out.println("Na które konto chcesz dokonąc wpłaty?");
                        for (int i = 0; i < accounts.size(); i++) {
                            System.out.println(i + 1 + " - " + accounts.get(i).getType());
                        }
                        int index = scanner.nextInt();

                        System.out.println("Podaj kwotę ");
                        double amount = scanner.nextDouble();


                        try {
                            bankService.payment(accounts, amount, index);
                            System.out.println("Wykonano wpłatę na kwotę? " + amount);
                        } catch (IllegalArgumentException | IndexValidationException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                    case 3: {
                        List<Account> accounts = client.getAccounts();
                        System.out.println("Z którego konta chcesz dokonać wypłaty? ");
                        for (int i = 0; i < accounts.size(); i++) {
                            System.out.println(i + 1 + " - " + accounts.get(i).getType());

                        }
                        int index = scanner.nextInt();

                        System.out.println("Podaj kwotę");
                        double amount = scanner.nextDouble();

                        try {
                            bankService.withdrawal(accounts, amount, index);
                        } catch (InsufficientBalanceException | NegativeAmountException | IndexValidationException exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    break;

                    case 4: {
                        List<Account> accounts = client.getAccounts();
                        System.out.println("Z którego konta chcesz dokonać przelewu? ");
                        for (int i = 0; i < accounts.size(); i++) {
                            System.out.println(i + 1 + " - " + accounts.get(i).getType());

                        }

                        int index = scanner.nextInt();

                        System.out.println("Podaj kwotę");
                        double amount = scanner.nextDouble();

                        System.out.println("podaj numer konta");
                        String targetAccount = scanner.next();

                        try {
                            bankService.transfer(accounts, amount, index, targetAccount);
                        } catch (InvalidAccountNumberException | IndexValidationException | InsufficientBalanceException | NegativeAmountException e) {
                            System.out.println(e.getMessage());

                        }
                    }
                    break;

                    default:
                        System.out.println("Podana wartość jest nieobsługiwana");
                        break;

                }
            } while (true);

        } else {

            System.out.println("Logowanie się  nie powiodło");
            System.exit(0);

        }
    }
}




