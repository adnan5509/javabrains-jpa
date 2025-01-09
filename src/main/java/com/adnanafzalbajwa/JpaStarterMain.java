package com.adnanafzalbajwa;

import enums.EmployeeType;
import model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class JpaStarterMain {

    public static void main(String[] args) {
//        Get Entity Manager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        Create
        Employee employee1 = new Employee();
        employee1.setName("Adnan");
        employee1.setAge(31);
        employee1.setDob(new Date());
        employee1.setEmployeeType(EmployeeType.CONTRACT);

        Employee employee2 = new Employee();
        employee2.setName("Zeshan");
        employee2.setAge(32);
        employee2.setDob(new Date());
        employee2.setEmployeeType(EmployeeType.FULL_TIME);

        Employee employee3 = new Employee();
        employee3.setName("Kamran");
        employee3.setAge(27);
        employee3.setDob(new Date());
        employee3.setEmployeeType(EmployeeType.CONTRACT);

        EntityTransaction entityTransactionSelect = entityManager.getTransaction();
        entityTransactionSelect.begin();
        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.persist(employee3);
        entityTransactionSelect.commit();

//        Retrieve
        Employee fetchedEmployee = entityManager.find(Employee.class, 2);
        System.out.println(fetchedEmployee);

//        Update
        EntityTransaction entityTransactionUpdate  = entityManager.getTransaction();
        entityTransactionUpdate.begin();
        fetchedEmployee.setAge(33);
        fetchedEmployee.setEmployeeType(EmployeeType.CONTRACT);
        entityManager.persist(fetchedEmployee);
        entityTransactionUpdate.commit();

//        Delete
        EntityTransaction entityTransactionDelete = entityManager.getTransaction();
        entityTransactionDelete.begin();
        Employee employeeToDelete = entityManager.find(Employee.class,3);
        entityManager.remove(employeeToDelete);
        entityTransactionDelete.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
