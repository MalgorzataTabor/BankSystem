package service;

import entity.Client;

import java.util.List;

public class LoginService {

    private List<Client>clients;

    public LoginService(List<Client> clients) {
        this.clients = clients;
    }


    public boolean isAuthenticated(String login, String password){
        for(Client client:clients){
            if(client.getLogin().equals(login) && client.getPassword().equals(password)){
                return true;
            }
        }
        return false;

    }
    public Client getClientByLoginAndPassword(String login, String password){

        return clients.stream()
                .filter(c->c.getLogin().equals(login) && c.getPassword().equals(password))
                .findFirst()
                .get();
    }
}
