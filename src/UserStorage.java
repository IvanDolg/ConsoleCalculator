import java.util.Optional;
import java.util.UUID;

public interface UserStorage {
    void save(User user);
    Optional<User> find(String username, String password);
}
