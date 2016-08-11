import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// в классе ДАО реализуем все методы для доступа в базу данных

public class EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private final String url = "jdbc:postgresql://localhost:5432/RESTAURANT";
    private final String user = "user";
    private final String password = "111";


    public EmployeeDao() {
        loadDriver();

    }

    // Динамический запрос
// достаем сотрудника по id              PREPARED STATEMENT
    public Employee load(int id){
        try (
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE ID = ? ")){
            preparedStatement.setInt(1, id); /* передаем параметры - 1- номер параметра (? - параметр по счету)
                                              id - параметр*/
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return createEmployee(resultSet);
            }else {
                throw new RuntimeException("Can't find Employee with id " + id);
            }

        } catch (SQLException e) {
                LOGGER.error("Exception occurred while connection to DB: " + url, e);
                throw new RuntimeException(e);
        }
    }





// простой запрос
    public List<Employee> getAll(){

        List<Employee> result = new ArrayList<>();

      //  LOGGER.info("Connection to DB");
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()){  /* чтобы выполнить запрос в БД необходимо у connection
                                                                    вызвать Statement*/

            ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEE");// результат запроса получаем в обертке, потом итерируемся по нему

            while (resultSet.next()){  //  пока есть результаты
                Employee employee = createEmployee(resultSet);
                employee.setSalary(resultSet.getFloat("SALARY"));

                result.add(employee);

//                System.out.println(employee.toString());
            }


           // LOGGER.info("Connected successfully to DB"); // info - уровень логирования. Выставляеться в конфигурации logback

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connection to DB: " + url, e);
            throw new RuntimeException(e);
        }
        return result;
    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException { // метод для создания Employee
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("ID"));  // достаем результаті по названию колонок
        employee.setSecond_name(resultSet.getString("SECOND_NAME"));
        employee.setName(resultSet.getString("NAME"));
        employee.setBirthday(resultSet.getString("BIRTHDAY"));
        employee.setTelephone(resultSet.getInt("TELEPHONE"));
        employee.setPosition(resultSet.getString("POSITION_ID"));
        return employee;
    }

    private void loadDriver() {
        try {
            LOGGER.info("Loading JDBC driver: org.postgresql.Driver");
            Class.forName("org.postgresql.Driver");  // driver loading
            LOGGER.info("Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot find driver: org.postgresql.Driver");
            throw new RuntimeException(e);
        }
    }
}
