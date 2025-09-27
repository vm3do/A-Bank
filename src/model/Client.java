package model;
import java.util.ArrayList;
import java.util.List;

public class Client extends Person {

    private List<Account> accounts = new ArrayList<>();

    public Client(String name, String email, String phone, String password) {
        super(name, email, password);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

}
