import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// для логирования используем slf4j + logback
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDao();
        System.out.println("All employees");
        employeeDao.getAll().forEach(System.out::println);

        System.out.println("Employee with id 3");
        System.out.println(employeeDao.load(3));

    }


}
