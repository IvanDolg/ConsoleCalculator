import consoleMethods.ConsoleReader;
import consoleMethods.ConsoleWriter;
import interfaces.Reader;
import interfaces.Validation;
import interfaces.Writer;
import validation.NameValidation;
import validation.PasswordValidation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UserService {
    private final Reader reader = new ConsoleReader();
    private final Writer writer = new ConsoleWriter();
    private final UserStorage storage = new FileUserStorage();
    private final Validation nameValidation = new NameValidation();
    private final Validation passwordValidation = new PasswordValidation();

    public void singUp(){
        writer.write("Enter your login: ");
        String newLogin = reader.redType();
        while (!nameValidation.validate(newLogin)){
            writer.write("Wrong user name...");
            newLogin = reader.redType();
        }

        writer.write("Enter your password: ");
        String newPassword = reader.redType();
        while (!passwordValidation.validate(newPassword)){
            writer.write("Wrong password...");
            newPassword = reader.redType();
        }

        writer.write("Enter your id: ");
        int id = Integer.parseInt(reader.redType());
        User user = new User(newLogin, newPassword, id);
        storage.save(user);

        writer.write("Welcome " + user.getLogin() + "\n");
    }

    public void loginIn(){
        File file = new File("src/DocFile/Session.txt");

        writer.write("Enter your login: ");
        String login = reader.redType();
        writer.write("Enter your password: ");
        String password = reader.redType();

        try (FileReader fileReader = new FileReader(file)){
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null){
                String[] path = line.split(":");
                String registeredLogin = path[0];
                String registeredPassword = path[1];

                if (path.length == 2 && registeredLogin.equals(login) && registeredPassword.equals(password)){
                    writer.write("Welcome " + login + "\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
