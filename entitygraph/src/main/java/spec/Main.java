package spec;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        HibernateExecutor doInJpa = new HibernateExecutor();

        Department departmentToInsert = new Department(1);

        doInJpa.execute(entityManager -> entityManager.persist(departmentToInsert));

        Employee employee1 = new Employee(1, "employee one");
        departmentToInsert.addEmployee(employee1);

        doInJpa.execute(entityManager -> entityManager.persist(employee1));

        Employee employee2 = new Employee(2, "employee two");
        departmentToInsert.addEmployee(employee2);

        doInJpa.execute(entityManager -> entityManager.persist(employee2));

        Department department = doInJpa.getResult(entityManager -> {
            Map<String, Object> getEntityGraph = new HashMap<>();
            getEntityGraph.put("jakarta.persistence.fetchgraph", entityManager.getEntityGraph("department.employees"));
            return entityManager.find(Department.class, 1, getEntityGraph);
        });

        System.out.println(department);
        for (Employee emp : department.getEmployees()) {
            System.out.println(emp);
        }
    }

}
