import consoleMethods.ConsoleReader;
import consoleMethods.ConsoleWriter;
import interfaces.Reader;
import interfaces.Validation;
import interfaces.Writer;
import validation.NameValidation;
import validation.PasswordValidation;

public class UserService {
    private final Reader reader = new ConsoleReader();
    private final Writer writer = new ConsoleWriter();
    private final DatabaseUserStorage storage = new DatabaseUserStorage();
    private final Validation nameValidation = new NameValidation();
    private final Validation passwordValidation = new PasswordValidation();

    public User singUp(){
        writer.write("Enter login: ");
        String newLogin = reader.redType();

        while (!nameValidation.validate(newLogin)){
            writer.write("Wrong user name...");
            writer.write("Enter login: ");
            newLogin = reader.redType();
        }

        writer.write("Enter password: ");
        String newPassword = reader.redType();

        while (!passwordValidation.validate(newPassword)){
            writer.write("Wrong password...");
            writer.write("Enter password: ");
            newPassword = reader.redType();
        }

        User user = new User(newLogin, newPassword);

        if (storage.checkUserData(user)) {
            writer.write("The user exists)");
            user = null;
        } else {
            writer.write("Welcome " + user.getLogin() + "\n");
        }
        return user;
    }

    public User loginIn(){
        writer.write("Enter login: ");
        String login = reader.redType();

        while (!nameValidation.validate(login)){
            writer.write("Wrong user name...");
            writer.write("Enter login: ");
            login = reader.redType();
        }

        writer.write("Enter user password: ");
        String password = reader.redType();

        while (!passwordValidation.validate(password)){
            writer.write("Wrong password...");
            writer.write("Enter user password: ");
            password = reader.redType();
        }

        User user = new User(login, password);

        if (storage.checkUserData(user)) {
            return user;
        } else {
            writer.write("This user does not exist)");
            user = null;
            return user;
        }
    }
}
