package spec;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NamedEntityGraph(name = "department.employees",
    attributeNodes = @NamedAttributeNode("employees")
)
public class Department {

    @Id
    private Integer id;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

    public Department() {
        // Hibernate
    }

    public Department(Integer id) {
        this.id = id;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setDepartment(this);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setDepartment(null);
    }

    @Override
    public String toString() {
        return String.format("Department: %d", id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
