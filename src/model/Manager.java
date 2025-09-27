package model;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Person {

    private List<Client> clientsList = new ArrayList<>();

    public Manager(String name, String email, String phone, String password) {
        super(name, email, password);
    }

    public List<Client> getClientsList() {
        return clientsList;
    }

    public void addClient(Client client) {
        this.clientsList.add(client);
    }
    
}