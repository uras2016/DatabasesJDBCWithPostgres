package ua.goit.java.jdbc.model;

import java.util.List;

public interface EmployeeDao {
    // Динамический запрос
// достаем сотрудника по id              PREPARED STATEMENT
    Employee load(int id);

    // простой запрос
    List<Employee> getAll();
}
