package controller;

import model.Client;
import model.Account;
import model.enums.AccountType;
import service.AccountService;
import utils.DisplayUtils;
import repository.PersonRepository;
import java.util.Scanner;
import java.util.Optional;

public class ClientController {
    
    private final Client client;
    private final AccountService accountService;
    private final PersonRepository personRepository;
    private final Scanner scanner = new Scanner(System.in);
    
    public ClientController(Client client, PersonRepository personRepository) {
        this.client = client;
        this.accountService = new AccountService();
        this.personRepository = personRepository;
    }
    
    public void viewAccounts() {
        System.out.println("\n=== Your Accounts ===");
        DisplayUtils.displayAccounts(client.getAccounts());
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
        System.out.println("3. TRANSFER TO MY ACCOUNTS");
        System.out.println("4. TRANSFER TO OTHER CLIENTS");
        System.out.print("Choose transaction type (1-4): ");
        
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                handleDeposit();
                break;
            case "2":
                handleWithdrawal();
                break;
            case "3":
                handleInternalTransfer();
                break;
            case "4":
                handleExternalTransfer();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    private void handleDeposit() {
        if (client.getAccounts().isEmpty()) {
            System.out.println("No accounts found. Please create an account first.");
            return;
        }
        
        System.out.println("\n=== Deposit Money ===");
        Account account = selectAccount();
        if (account == null) return;
        
        try {
            System.out.print("Enter amount to deposit: $");
            double amount = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            
            accountService.deposit(account, amount, description);
            System.out.println("Deposit successful! New balance: $" + account.getBalance());
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void handleWithdrawal() {
        if (client.getAccounts().isEmpty()) {
            System.out.println("No accounts found. Please create an account first.");
            return;
        }
        
        System.out.println("\n=== Withdraw Money ===");
        Account account = selectAccount();
        if (account == null) return;
        
        try {
            System.out.print("Enter amount to withdraw: $");
            double amount = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            
            accountService.withdraw(account, amount, description);
            System.out.println("Withdrawal successful! New balance: $" + account.getBalance());
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void handleInternalTransfer() {
        if (client.getAccounts().size() < 2) {
            System.out.println("You need at least 2 accounts to make transfers.");
            return;
        }
        
        System.out.println("\n=== Transfer Money ===");
        System.out.println("Your Accounts:");
        DisplayUtils.displayAccountsWithIndex(client.getAccounts());
        
        try {
            System.out.print("Select FROM account (1-" + client.getAccounts().size() + "): ");
            int fromIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (fromIndex < 0 || fromIndex >= client.getAccounts().size()) {
                System.out.println("Invalid account selection.");
                return;
            }
            
            System.out.print("Select TO account (1-" + client.getAccounts().size() + "): ");
            int toIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (toIndex < 0 || toIndex >= client.getAccounts().size()) {
                System.out.println("Invalid account selection.");
                return;
            }
            
            if (fromIndex == toIndex) {
                System.out.println("Cannot transfer to the same account.");
                return;
            }
            
            Account fromAccount = client.getAccounts().get(fromIndex);
            Account toAccount = client.getAccounts().get(toIndex);
            
            System.out.print("Enter amount to transfer: $");
            double amount = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            
            accountService.transfer(fromAccount, toAccount, amount, description);
            System.out.println("Transfer successful!");
            System.out.println("From account balance: $" + fromAccount.getBalance());
            System.out.println("To account balance: $" + toAccount.getBalance());
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private Account selectAccount() {
        System.out.println("Your Accounts:");
        DisplayUtils.displayAccountsWithIndex(client.getAccounts());
        
        System.out.print("Select account (1-" + client.getAccounts().size() + "): ");
        try {
            int accountIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (accountIndex < 0 || accountIndex >= client.getAccounts().size()) {
                System.out.println("Invalid account selection.");
                return null;
            }
            return client.getAccounts().get(accountIndex);
        } catch (NumberFormatException e) {
            System.out.println("Invalid account selection.");
            return null;
        }
    }
    
    private void handleExternalTransfer() {
        if (client.getAccounts().isEmpty()) {
            System.out.println("No accounts found. Please create an account first.");
            return;
        }
        
        System.out.println("\n=== Transfer to Other Clients ===");
        
        // FROM account (client's own account)
        System.out.println("Your Accounts:");
        DisplayUtils.displayAccountsWithIndex(client.getAccounts());
        
        try {
            System.out.print("Select FROM account (1-" + client.getAccounts().size() + "): ");
            int fromIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (fromIndex < 0 || fromIndex >= client.getAccounts().size()) {
                System.out.println("Invalid account selection.");
                return;
            }
            
            Account fromAccount = client.getAccounts().get(fromIndex);
            
            System.out.print("Enter recipient's email: ");
            String recipientEmail = scanner.nextLine();
            
            //check if recipient exists
            Optional<model.Person> recipientPerson = personRepository.findByEmail(recipientEmail);
            if (recipientPerson.isEmpty()) {
                System.out.println("Recipient not found with email: " + recipientEmail);
                return;
            }
            
            if (!(recipientPerson.get() instanceof Client)) {
                System.out.println("Recipient is not a client.");
                return;
            }
            
            Client recipientClient = (Client) recipientPerson.get();
            
            // Check if recipient has accounts
            if (recipientClient.getAccounts().isEmpty()) {
                System.out.println("Recipient has no accounts.");
                return;
            }
            
            // Select TO account (recipient's account)
            System.out.println("\nRecipient's Accounts:");
            DisplayUtils.displayAccountsWithIndex(recipientClient.getAccounts());
            
            System.out.print("Select TO account (1-" + recipientClient.getAccounts().size() + "): ");
            int toIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (toIndex < 0 || toIndex >= recipientClient.getAccounts().size()) {
                System.out.println("Invalid account selection.");
                return;
            }
            
            Account toAccount = recipientClient.getAccounts().get(toIndex);
            
            // Get transfer details
            System.out.print("Enter amount to transfer: $");
            double amount = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            
            // Perform transfer
            accountService.transfer(fromAccount, toAccount, amount, description);
            System.out.println("Transfer successful!");
            System.out.println("From account balance: $" + fromAccount.getBalance());
            System.out.println("To account balance: $" + toAccount.getBalance());
            System.out.println("Transferred to: " + recipientClient.getName() + " (" + recipientEmail + ")");
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}