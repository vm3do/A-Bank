package view;

import model.Client;
import controller.ClientController;
import utils.SessionManager;
import java.util.Scanner;

public class ClientMenu {
    
    private final Client client;
    private final ClientController clientController;
    private final Scanner scanner = new Scanner(System.in);
    
    public ClientMenu(Client client) {
        this.client = client;
        this.clientController = new ClientController(client);
    }
    
    public void show() {
        while (true) {
            System.out.println("\n=== Client Dashboard ===");
            System.out.println("Welcome, " + client.getName() + "!");
            System.out.println("1. View Accounts");
            System.out.println("2. Create New Account");
            System.out.println("3. View Account Details");
            System.out.println("4. Make Transaction");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    clientController.viewAccounts();
                    break;
                case "2":
                    clientController.createAccount();
                    break;
                case "3":
                    clientController.viewAccountDetails();
                    break;
                case "4":
                    clientController.makeTransaction();
                    break;
                case "0":
                    System.out.println("Logging out...");
                    SessionManager.logout();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}