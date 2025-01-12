package com.adnanafzalbajwa;

import model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaJpqlDemo {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Employee> getAllEmployeeQuery = entityManager.createQuery("select e from Employee e", Employee.class);
        List<Employee> allEmployeeList = getAllEmployeeQuery.getResultList();
        allEmployeeList.forEach(System.out::println);


        TypedQuery<Employee> getEmployeesByAgeDescQuery = entityManager.createQuery("select e from Employee e order by e.age desc ", Employee.class);
        List<Employee> getEmployeesByAgeDescResultList = getEmployeesByAgeDescQuery.getResultList();
        getEmployeesByAgeDescResultList.forEach(System.out::println);


        TypedQuery<Employee> getEmployeesWithValidAccessCardQuery = entityManager.createQuery("select e from Employee e where e.accessCard.isActive = true ",
                Employee.class);
        List<Employee> getEmployeesWithValidAccessCardResultList = getEmployeesWithValidAccessCardQuery.getResultList();
        getEmployeesWithValidAccessCardResultList.forEach(System.out::println);


        TypedQuery<Object[]> getEmployeeNameAndAgeQuery = entityManager.createQuery("select e.name,e.age, e.accessCard.issueDate from " +
                        "Employee e",
                Object[].class);
        List<Object[]> getEmployeeNameAndAgeResultList = getEmployeeNameAndAgeQuery.getResultList();
        getEmployeeNameAndAgeResultList.forEach(e -> System.out.println("Employee Name: " + e[0] + " , Employee Age: " + e[1] + " , Access Card Issue " +
                "Date: " + e[2]));


        TypedQuery<Object[]> getEmployeeNameAndPayslipsQuery = entityManager.createQuery("select e.name, p.salary from Employee e JOIN " +
                        "PaySlip p ON e.id = p.employee.id",
                Object[].class);
        List<Object[]> getEmployeeNameAndPayslipsResultList = getEmployeeNameAndPayslipsQuery.getResultList();
        getEmployeeNameAndPayslipsResultList.forEach(e -> System.out.println("Employee Name: " + e[0] + " , Employee Salary: " + e[1]));


//        Parametrized query to avoid sql injection
        int ageGiven = 31;
        TypedQuery<Employee> employeesOlderThanGivenAgeQuery = entityManager.createQuery("SELECT e FROM Employee e WHERE e.age > :ageGiven",
                Employee.class);
        employeesOlderThanGivenAgeQuery.setParameter("ageGiven", ageGiven);
        List<Employee> employeesOlderThanGivenAgeResultList = employeesOlderThanGivenAgeQuery.getResultList();
        employeesOlderThanGivenAgeResultList.forEach(System.out::println);

//        Named query (Get total salary drew by a given employee)
        Employee employeeFetched = entityManager.find(Employee.class, 1);
        TypedQuery<Double> employeeTotalEarnings = entityManager.createNamedQuery("employeeTotalEarnings", Double.class);
        employeeTotalEarnings.setParameter("givenEmployee", employeeFetched.getId());
        List<Double> employeeTotalEarningsResultList = employeeTotalEarnings.getResultList();
        System.out.println("Total Salary withdrew by " + employeeFetched.getName() + " amounts to: " + employeeTotalEarningsResultList.get(0));


    }
}
