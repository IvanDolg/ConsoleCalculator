import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DatabaseUserStorage {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String sqlRequest = "insert into \"userData\" (user_name, password) values (?, ?)";
    private final String sqlRequest1 = "insert into \"userData\" (num1, num2, type, result, user_name, password) VALUES (?,?,?,?,?,?)";
    private final String sqlRequest2 = "select num1, num2, type, result from \"userData\" where user_name = ? and password = ? is not null";
    private final String sqlRequest3 = "select distinct user_name, password from \"userData\"";

    public void setUserData(User user) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)){

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData(User user, Operation operation) {
        try (Connection connection1 = DriverManager.getConnection(url, "postgres", "root");
             PreparedStatement preparedStatement = connection1.prepareStatement(sqlRequest1)) {

            preparedStatement.setDouble(1, operation.getNum1());
            preparedStatement.setDouble(2, operation.getNum2());
            preparedStatement.setString(3, operation.getType());
            preparedStatement.setDouble(4, operation.getResult());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkUserData (User user) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest3)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("user_name").equals(user.getLogin()) && resultSet.getString("password").equals(user.getPassword()))
                    return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Operation> getAll(User user) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest2)) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Operation> history = new ArrayList<>();

            Operation newOperation;
            while (resultSet.next()) {
                newOperation = new Operation(resultSet.getDouble("num1"), resultSet.getDouble("num2"),
                        resultSet.getString("type"), (int) resultSet.getDouble("result"));

                history.add(newOperation);
            }
            return history;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
