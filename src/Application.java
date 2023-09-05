import consoleMethods.ConsoleReader;
import consoleMethods.ConsoleWriter;
import interfaces.Reader;
import interfaces.Writer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    private final Reader reader = new ConsoleReader();
    private final Writer writer = new ConsoleWriter();
    private OperationService operationService;
    private final UserService userService = new UserService();
    private DatabaseUserStorage storage = new DatabaseUserStorage();
    private Session session;
    private  boolean mainCondition = true;

    public void showGuestMenu(){
        writer.write("1) Sign up");
        writer.write("\n2) Log in");
        writer.write("\n3) Exit");
        writer.write("\nChoose your action: ");
    }
    public void showUserMenu(){
        writer.write("\nResume? " +
                "\n1 - Yes, " +
                "\n2 - No, " +
                "\n3 - show history" +
                "\nYour choice: ");
    }
    public void start(){
        while (mainCondition) {
            if (session == null){
                GuestMenu();
            } else {
                userMenu();
            }
        }
    }
    private void GuestMenu() {
        boolean condition = true;
        while (condition){
           showGuestMenu();
            int action = (int) reader.readNumber();
            switch (action){
                case 1:
                    User user1 = userService.singUp();
                    if (user1 == null){
                        writer.write("There is no user data");
                    } else {
                        storage.setUserData(user1);
                    }
                    break;
                case 2:
                    User user = userService.loginIn();
                    if (user == null){
                        writer.write("Error....");
                    } else {
                        session = new Session(user);
                        operationService = new OperationService(user);

                        condition = false;
                    }
                    break;
                case 3:
                    condition = false;
                    mainCondition = false;
                    break;
                default:
                    writer.write("Enter correct number) ");
                    break;
            }
        }
    }
    private void userMenu(){
        boolean condition = true;
        while (condition){
            operationService.newOperation();
            showUserMenu();
            double answer = reader.readNumber();
            if (answer == 2){
                session = null;
                condition = false;
            }
            if (answer == 3){
                List<String> history = operationService.getHistory();
                for (String operationHistory : history){
                    writer.write(operationHistory);
                }
                break;
            }
        }
    }
}

