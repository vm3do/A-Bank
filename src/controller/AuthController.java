package controller;

import model.Person;
import model.Client;
import model.Manager;
import service.AuthService;
import java.util.Scanner;

public class AuthController {
    
    // private final AuthService authService;
    // private final Scanner scanner;
    // private Person currentUser;
    
    // public AuthController(AuthService authService) {
    //     this.authService = authService;
    //     this.scanner = new Scanner(System.in);
    //     this.currentUser = null;
    // }
    
    // /**
    //  * Display the main authentication menu
    //  */
    // public void showAuthMenu() {
    //     while (true) {
    //         System.out.println("\n=== Banking System Authentication ===");
    //         System.out.println("1. Login");
    //         System.out.println("2. Register as Client");
    //         System.out.println("3. Register as Manager");
    //         System.out.println("4. Exit");
    //         System.out.print("Choose an option: ");
            
    //         int choice = getIntInput();
            
    //         switch (choice) {
    //             case 1:
    //                 handleLogin();
    //                 break;
    //             case 2:
    //                 handleClientRegistration();
    //                 break;
    //             case 3:
    //                 handleManagerRegistration();
    //                 break;
    //             case 4:
    //                 System.out.println("Thank you for using Banking System!");
    //                 return;
    //             default:
    //                 System.out.println("Invalid option. Please try again.");
    //         }
    //     }
    // }
    
    // /**
    //  * Handle user login
    //  */
    // private void handleLogin() {
    //     System.out.println("\n=== Login ===");
    //     System.out.print("Email: ");
    //     String email = scanner.nextLine().trim();
        
    //     System.out.print("Password: ");
    //     String password = scanner.nextLine().trim();
        
    //     if (email.isEmpty() || password.isEmpty()) {
    //         System.out.println("❌ Email and password cannot be empty.");
    //         return;
    //     }
        
    //     try {
    //         Person authenticatedUser = authService.authenticate(email, password);
    //         if (authenticatedUser != null) {
    //             this.currentUser = authenticatedUser;
    //             System.out.println("✅ Login successful! Welcome, " + authenticatedUser.getName());
                
    //             // Redirect to appropriate menu based on user type
    //             if (authenticatedUser instanceof Client) {
    //                 System.out.println("Redirecting to Client Menu...");
    //                 // Here you would call clientController.showClientMenu()
    //             } else if (authenticatedUser instanceof Manager) {
    //                 System.out.println("Redirecting to Manager Menu...");
    //                 // Here you would call managerController.showManagerMenu()
    //             }
    //         } else {
    //             System.out.println("❌ Invalid email or password.");
    //         }
    //     } catch (Exception e) {
    //         System.out.println("❌ Login failed: " + e.getMessage());
    //     }
    // }
    
    // /**
    //  * Handle client registration
    //  */
    // private void handleClientRegistration() {
    //     System.out.println("\n=== Client Registration ===");
    //     Person newClient = collectPersonInfo();
    //     if (newClient != null) {
    //         try {
    //             Client client = new Client(newClient.getName(), newClient.getEmail(), 
    //                                      newClient.getPhone(), newClient.getPassword());
    //             authService.register(client);
    //             System.out.println("✅ Client registration successful! You can now login.");
    //         } catch (Exception e) {
    //             System.out.println("❌ Registration failed: " + e.getMessage());
    //         }
    //     }
    // }
    
    // /**
    //  * Handle manager registration
    //  */
    // private void handleManagerRegistration() {
    //     System.out.println("\n=== Manager Registration ===");
    //     Person newManager = collectPersonInfo();
    //     if (newManager != null) {
    //         try {
    //             Manager manager = new Manager(newManager.getName(), newManager.getEmail(), 
    //                                         newManager.getPhone(), newManager.getPassword());
    //             authService.register(manager);
    //             System.out.println("✅ Manager registration successful! You can now login.");
    //         } catch (Exception e) {
    //             System.out.println("❌ Registration failed: " + e.getMessage());
    //         }
    //     }
    // }
    
    // /**
    //  * Collect person information for registration
    //  */
    // private Person collectPersonInfo() {
    //     System.out.print("Full Name: ");
    //     String name = scanner.nextLine().trim();
        
    //     System.out.print("Email: ");
    //     String email = scanner.nextLine().trim();
        
    //     System.out.print("Phone: ");
    //     String phone = scanner.nextLine().trim();
        
    //     System.out.print("Password: ");
    //     String password = scanner.nextLine().trim();
        
    //     System.out.print("Confirm Password: ");
    //     String confirmPassword = scanner.nextLine().trim();
        
    //     // Validation
    //     if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
    //         System.out.println("❌ All fields are required.");
    //         return null;
    //     }
        
    //     if (!password.equals(confirmPassword)) {
    //         System.out.println("❌ Passwords do not match.");
    //         return null;
    //     }
        
    //     if (password.length() < 6) {
    //         System.out.println("❌ Password must be at least 6 characters long.");
    //         return null;
    //     }
        
    //     // Create a temporary Person object to return the data
    //     return new Client(name, email, phone, password);
    // }
    
    // /**
    //  * Handle password change
    //  */
    // public void handlePasswordChange() {
    //     if (currentUser == null) {
    //         System.out.println("❌ You must be logged in to change password.");
    //         return;
    //     }
        
    //     System.out.println("\n=== Change Password ===");
    //     System.out.print("Current Password: ");
    //     String currentPassword = scanner.nextLine().trim();
        
    //     System.out.print("New Password: ");
    //     String newPassword = scanner.nextLine().trim();
        
    //     System.out.print("Confirm New Password: ");
    //     String confirmPassword = scanner.nextLine().trim();
        
    //     if (!newPassword.equals(confirmPassword)) {
    //         System.out.println("❌ New passwords do not match.");
    //         return;
    //     }
        
    //     if (newPassword.length() < 6) {
    //         System.out.println("❌ New password must be at least 6 characters long.");
    //         return;
    //     }
        
    //     try {
    //         boolean success = authService.changePassword(currentUser.getEmail(), currentPassword, newPassword);
    //         if (success) {
    //             System.out.println("✅ Password changed successfully!");
    //         } else {
    //             System.out.println("❌ Current password is incorrect.");
    //         }
    //     } catch (Exception e) {
    //         System.out.println("❌ Failed to change password: " + e.getMessage());
    //     }
    // }
    
    // /**
    //  * Logout current user
    //  */
    // public void logout() {
    //     if (currentUser != null) {
    //         System.out.println("👋 Goodbye, " + currentUser.getName() + "!");
    //         currentUser = null;
    //     } else {
    //         System.out.println("You are not logged in.");
    //     }
    // }
    
    // /**
    //  * Get current logged-in user
    //  */
    // public Person getCurrentUser() {
    //     return currentUser;
    // }
    
    // /**
    //  * Check if user is logged in
    //  */
    // public boolean isLoggedIn() {
    //     return currentUser != null;
    // }
    
    // /**
    //  * Check if current user is a client
    //  */
    // public boolean isClient() {
    //     return currentUser instanceof Client;
    // }
    
    // /**
    //  * Check if current user is a manager
    //  */
    // public boolean isManager() {
    //     return currentUser instanceof Manager;
    // }
    
    // /**
    //  * Get current user as Client (with type safety)
    //  */
    // public Client getCurrentClient() {
    //     if (isClient()) {
    //         return (Client) currentUser;
    //     }
    //     return null;
    // }
    
    // /**
    //  * Get current user as Manager (with type safety)
    //  */
    // public Manager getCurrentManager() {
    //     if (isManager()) {
    //         return (Manager) currentUser;
    //     }
    //     return null;
    // }
    
    // /**
    //  * Helper method to get integer input with error handling
    //  */
    // private int getIntInput() {
    //     try {
    //         String input = scanner.nextLine().trim();
    //         return Integer.parseInt(input);
    //     } catch (NumberFormatException e) {
    //         return -1; // Invalid input
    //     }
    // }
    
}