package com.adnanafzalbajwa;

import model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaStarterMain {

    public static void main(String[] args) {

        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setName("Adnan");

        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setName("Adnan");

        Employee employee3 = new Employee();
        employee3.setId(3);
        employee3.setName("Kamran");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.persist(employee3);
        entityTransaction.commit();

    }
}
