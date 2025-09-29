package view;

import model.Manager;
import controller.ManagerController;
import repository.PersonRepository;
import java.util.Scanner;

public class ManagerMenu {
    
    private final Manager manager;
    private final ManagerController managerController;
    private final Scanner scanner = new Scanner(System.in);
    
    public ManagerMenu(Manager manager, PersonRepository personRepository) {
        this.manager = manager;
        this.managerController = new ManagerController(manager, personRepository);
    }
    
    public void show() {
        while (true) {
            System.out.println("\n=== Manager Dashboard ===");
            System.out.println("Welcome, " + manager.getName() + "!");
            System.out.println("1. View All Clients");
            System.out.println("2. Register New Client");
            System.out.println("3. View Client Details");
            System.out.println("4. View All Accounts");
            System.out.println("5. View All Transactions");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    managerController.viewAllClients();
                    break;
                case "2":
                    managerController.registerClient();
                    break;
                case "3":
                    managerController.viewClientDetails();
                    break;
                case "4":
                    managerController.viewAllAccounts();
                    break;
                case "5":
                    managerController.viewAllTransactions();
                    break;
                case "0":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
