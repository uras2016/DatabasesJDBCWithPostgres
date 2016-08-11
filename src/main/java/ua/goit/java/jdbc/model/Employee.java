package ua.goit.java.jdbc.model;

public class Employee {

    private int id;
    private String second_name;
    private String name;
    private String birthday;
    private int telephone;
    private String position;
    private float salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ua.goit.java.jdbc.model.Employee{" +
                "id=" + id +
                ", second_name='" + second_name + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", telephone=" + telephone +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}
