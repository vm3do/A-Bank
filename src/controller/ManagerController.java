package controller;

import model.Manager;
import model.Client;
import model.Account;
import model.Transaction;
import service.AuthService;
import service.ClientService;
import service.AccountService;
import repository.PersonRepository;
import java.util.List;
import java.util.Scanner;

public class ManagerController {
    
    private final Manager manager;
    private final AuthService authService;
    private final ClientService clientService;
    private final PersonRepository personRepository;
    private final AccountService accountService;
    private final Scanner scanner = new Scanner(System.in);
    
    public ManagerController(Manager manager, PersonRepository personRepository) {
        this.manager = manager;
        this.personRepository = personRepository;
        this.authService = new AuthService(personRepository);
        this.clientService = new ClientService();
        this.accountService = new AccountService();
    }
    
    public void viewAllClients() {
        System.out.println("\n=== All Clients ===");
        if (manager.getClientsList().isEmpty()) {
            System.out.println("No clients found.");
        } else {
            for (Client client : manager.getClientsList()) {
                System.out.println("Client: " + client.getName());
                System.out.println("Email: " + client.getEmail());
                System.out.println("Accounts: " + client.getAccounts().size());
                System.out.println("---");
            }
        }
    }
    
    public void registerClient() {
        System.out.println("\n=== Register New Client ===");
        System.out.print("Enter client name: ");
        String name = scanner.nextLine();
        System.out.print("Enter client email: ");
        String email = scanner.nextLine();
        System.out.print("Enter client password: ");
        String password = scanner.nextLine();
        
        try {
            Client client = new Client(name, email, password);
            authService.registerClient(client);
            manager.addClient(client);
            System.out.println("Client registered successfully!");
            System.out.println("Client ID: " + client.getId());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void viewClientDetails() {
        System.out.println("\n=== Client Details ===");
        if (manager.getClientsList().isEmpty()) {
            System.out.println("No clients found.");
            return;
        }
        
        System.out.print("Enter client email: ");
        String email = scanner.nextLine();
        
        Client client = manager.getClientsList().stream()
            .filter(c -> c.getEmail().equals(email))
            .findFirst()
            .orElse(null);
            
        if (client == null) {
            System.out.println("Client not found.");
            return;
        }
        
        System.out.println("Client Name: " + client.getName());
        System.out.println("Email: " + client.getEmail());
        System.out.println("Accounts: " + client.getAccounts().size());
        
        if (!client.getAccounts().isEmpty()) {
            System.out.println("\nClient Accounts:");
            for (Account account : client.getAccounts()) {
                System.out.println("- Account ID: " + account.getId());
                System.out.println("  Type: " + account.getAccountType());
                System.out.println("  Balance: $" + account.getBalance());
            }
        }
    }
    
    public void viewAllAccounts() {
        System.out.println("\n=== All Accounts ===");
        if (manager.getClientsList().isEmpty()) {
            System.out.println("No clients found.");
            return;
        }
        
        for (Client client : manager.getClientsList()) {
            if (!client.getAccounts().isEmpty()) {
                System.out.println("Client: " + client.getName());
                for (Account account : client.getAccounts()) {
                    System.out.println("- Account ID: " + account.getId());
                    System.out.println("  Type: " + account.getAccountType());
                    System.out.println("  Balance: $" + account.getBalance());
                }
                System.out.println("---");
            }
        }
    }
    
    public void viewAllTransactions() {
        System.out.println("\n=== All Transactions ===");
        if (manager.getClientsList().isEmpty()) {
            System.out.println("No clients found.");
            return;
        }
        
        boolean hasTransactions = false;
        for (Client client : manager.getClientsList()) {
            if (!client.getAccounts().isEmpty()) {
                for (Account account : client.getAccounts()) {
                    List<Transaction> transactions = accountService.getAccountTransactions(account);
                    if (!transactions.isEmpty()) {
                        hasTransactions = true;
                        System.out.println("\nClient: " + client.getName());
                        System.out.println("Account: " + account.getAccountType() + " (ID: " + account.getId() + ")");
                        System.out.println("Transactions:");
                        for (Transaction transaction : transactions) {
                            System.out.println("- " + transaction.getType() + ": $" + transaction.getAmount());
                            System.out.println("  Date: " + transaction.getDate());
                            System.out.println("  Description: " + transaction.getDescription());
                            System.out.println("  ---");
                        }
                    }
                }
            }
        }
        
        if (!hasTransactions) {
            System.out.println("No transactions found.");
        }
    }
}