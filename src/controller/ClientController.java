package controller;

import model.Client;
import model.Account;
import model.enums.AccountType;
import service.AccountService;
import service.ClientService;
import java.util.Scanner;

public class ClientController {
    
    private final Client client;
    private final AccountService accountService;
    private final ClientService clientService;
    private final Scanner scanner = new Scanner(System.in);
    
    public ClientController(Client client) {
        this.client = client;
        this.accountService = new AccountService();
        this.clientService = new ClientService();
    }
    
    public void viewAccounts() {
        System.out.println("\n=== Your Accounts ===");
        if (client.getAccounts().isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Account account : client.getAccounts()) {
                System.out.println("Account ID: " + account.getId());
                System.out.println("Type: " + account.getAccountType());
                System.out.println("Balance: $" + account.getBalance());
                System.out.println("---");
            }
        }
    }
    
    public void createAccount() {
        System.out.println("\n=== Create New Account ===");
        System.out.println("Account Types:");
        System.out.println("1. SAVINGS");
        System.out.println("2. CHECKING");
        System.out.println("3. BUSINESS");
        System.out.print("Choose account type (1-3): ");
        
        String choice = scanner.nextLine();
        AccountType accountType;
        
        switch (choice) {
            case "1":
                accountType = AccountType.SAVINGS;
                break;
            case "2":
                accountType = AccountType.CHECKING;
                break;
            case "3":
                accountType = AccountType.BUSINESS;
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        
        System.out.print("Enter initial balance: $");
        try {
            double initialBalance = Double.parseDouble(scanner.nextLine());
            if (initialBalance < 0) {
                System.out.println("Initial balance cannot be negative.");
                return;
            }
            
            Account account = new Account(initialBalance, accountType, client);
            client.addAccount(account);
            System.out.println("Account created successfully!");
            System.out.println("Account ID: " + account.getId());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format.");
        }
    }
    
    public void viewAccountDetails() {
        System.out.println("\n=== Account Details ===");
        if (client.getAccounts().isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        
        System.out.print("Enter Account ID: ");
        String accountId = scanner.nextLine();
        
        Account account = client.getAccounts().stream()
            .filter(a -> a.getId().toString().equals(accountId))
            .findFirst()
            .orElse(null);
            
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        
        System.out.println("Account ID: " + account.getId());
        System.out.println("Type: " + account.getAccountType());
        System.out.println("Balance: $" + account.getBalance());
        System.out.println("Owner: " + account.getOwner().getName());
    }
    
    public void makeTransaction() {
        System.out.println("\n=== Make Transaction ===");
        System.out.println("Transaction types:");
        System.out.println("1. DEPOSIT");
        System.out.println("2. WITHDRAWAL");
        System.out.println("3. TRANSFER");
        System.out.print("Choose transaction type (1-3): ");
        
        String choice = scanner.nextLine();
        // todo: transaction functionality
        System.out.println("Selected type: " + choice);
    }
}