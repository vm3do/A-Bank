import controller.AuthController;
import service.AuthService;
import repository.PersonRepository;
import repository.impl.PersonRepositoryImpl;

public class Main {
    public static void main(String[] args) {

        PersonRepository personRepository = new PersonRepositoryImpl();
        AuthService authService = new AuthService(personRepository);
        AuthController authController = new AuthController(authService, personRepository);
        
        authController.showMainMenu();
    }
}