package utils;

import model.Account;
import java.util.List;

public class DisplayUtils {
    
    public static void displayAccount(Account account) {
        System.out.println("- Account ID: " + account.getId());
        System.out.println("  Type: " + account.getAccountType());
        System.out.println("  Balance: $" + account.getBalance());
    }
    
    public static void displayAccounts(List<Account> accounts) {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Account account : accounts) {
                displayAccount(account);
                System.out.println("---");
            }
        }
    }
    
    public static void displayAccountsWithIndex(List<Account> accounts) {
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            System.out.println((i + 1) + ". " + account.getAccountType() + " - Balance: $" + account.getBalance());
        }
    }
}
