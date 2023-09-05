import java.util.UUID;

public class User {
    private String login;
    private String password;
    private UUID userId;
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, UUID userId) {
        this.login = login;
        this.password = password;
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return  login + ":" +
                password + "\n";
    }
    public String toNewString() {
        return  userId + ":" +
                login + ":" +
                password + "\n";
    }
}
