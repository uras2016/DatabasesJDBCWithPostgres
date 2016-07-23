import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        loadDriver();
        LOGGER.info("Connection to DB");
        String url = "jdbc:postgresql://localhost:5432/Restaurant";
        String user = "user";
        String password = "111";
        try (Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement()){
            String sql = "SELECT * FROM EMPLOYEE ORDER BY ID ASC";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("ID"));
                employee.setSecond_name(resultSet.getString("SECOND_NAME"));
                employee.setName(resultSet.getString("NAME"));
                employee.setBirthday(resultSet.getString("BIRTHDAY"));
                employee.setTelephone(resultSet.getInt("TELEPHONE"));
                employee.setPosition(resultSet.getString("POSITION"));
                employee.setSalary(resultSet.getFloat("SALARY"));
                System.out.println(employee.toString());
                }
            LOGGER.info("Connected successfully to DB");

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connection to DB: " + url, e);
        }
    }

    private static void loadDriver() {
        try {
            LOGGER.info("Loading JDBC driver: org.postgresql.Driver");
            Class.forName("org.postgresql.Driver");
            LOGGER.info("Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot find driver: org.postgresql.Driver");
            throw new RuntimeException(e);
        }
    }
}
