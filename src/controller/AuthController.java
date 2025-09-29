package controller;

import model.Client;
import model.Manager;
import model.Person;
import service.AuthService;
import utils.SessionManager;
import view.ClientMenu;
import view.ManagerMenu;
import repository.PersonRepository;

import java.util.Optional;
import java.util.Scanner;

public class AuthController {

    private final AuthService authService;
    private final PersonRepository personRepository;
    private final Scanner scanner = new Scanner(System.in);

    public AuthController(AuthService authService, PersonRepository personRepository) {
        this.authService = authService;
        this.personRepository = personRepository;
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("\n=== A-Bank Authentication ===");
            System.out.println("1. Login");
            System.out.println("2. Register Manager");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleLogin();
                    break;
                case "2":
                    handleRegisterManager();
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
    private void handleRegisterManager() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            Manager manager = new Manager(name, email, password);
            authService.registerManager(manager);
            System.out.println("Manager registered successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleLogin() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            Optional<Person> loggedIn = authService.login(email, password);

            if (loggedIn.isEmpty()) {
                System.out.println("Invalid credentials.");
                return;
            }

            Person person = loggedIn.get();
            SessionManager.setCurrentUser(person);
            
            if (person instanceof Manager) {
                System.out.println("Manager logged in: " + person.getName());
                new ManagerMenu((Manager) person, personRepository).show();
            } else if (person instanceof Client) {
                System.out.println("Client logged in: " + person.getName());
                new ClientMenu((Client) person).show();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void handleLogout() {
        SessionManager.logout();
        System.out.println("Successfully logged out.");
    }

}
