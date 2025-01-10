package com.adnanafzalbajwa;

import enums.EmployeeType;
import model.AccessCard;
import model.Employee;
import model.PaySlip;
import model.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

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

        AccessCard accessCard1 = new AccessCard();
        accessCard1.setIssueDate(new Date());
        accessCard1.setActive(true);
        accessCard1.setFirmwareVersion("1.0.0.0");

        AccessCard accessCard2 = new AccessCard();
        accessCard2.setIssueDate(new Date());
        accessCard2.setActive(false);
        accessCard2.setFirmwareVersion("1.0.0.1");

        AccessCard accessCard3 = new AccessCard();
        accessCard3.setIssueDate(new Date());
        accessCard3.setActive(true);
        accessCard3.setFirmwareVersion("1.0.0.2");

        PaySlip paySlip1 = new PaySlip();
        paySlip1.setPeriodStartDate(new Date());
        paySlip1.setPeriodEndDate(new Date());
        paySlip1.setSalary(1000);

        PaySlip paySlip2 = new PaySlip();
        paySlip2.setPeriodStartDate(new Date());
        paySlip2.setPeriodEndDate(new Date());
        paySlip2.setSalary(2000);

        PaySlip paySlip3 = new PaySlip();
        paySlip3.setPeriodStartDate(new Date());
        paySlip3.setPeriodEndDate(new Date());
        paySlip3.setSalary(3000);

        PaySlip paySlip4 = new PaySlip();
        paySlip4.setPeriodStartDate(new Date());
        paySlip4.setPeriodEndDate(new Date());
        paySlip4.setSalary(4000);

        PaySlip paySlip5 = new PaySlip();
        paySlip5.setPeriodStartDate(new Date());
        paySlip5.setPeriodEndDate(new Date());
        paySlip5.setSalary(5000);

        PaySlip paySlip6 = new PaySlip();
        paySlip6.setPeriodStartDate(new Date());
        paySlip6.setPeriodEndDate(new Date());
        paySlip6.setSalary(6000);

        Team team1 = new Team();
        team1.setName("NOW");
        team1.addTeamMember(employee1);
        team1.addTeamMember(employee2);
        employee1.assignTeamToEmployee(team1);
        employee2.assignTeamToEmployee(team1);

        Team team2 = new Team();
        team2.setName("CALMS");
        team2.addTeamMember(employee1);
        team2.addTeamMember(employee2);
        team2.addTeamMember(employee3);
        employee1.assignTeamToEmployee(team2);
        employee2.assignTeamToEmployee(team2);
        employee3.assignTeamToEmployee(team2);

        paySlip1.setEmployee(employee1);
        employee1.addPaySlip(paySlip1);

        paySlip2.setEmployee(employee1);
        employee1.addPaySlip(paySlip2);

        paySlip3.setEmployee(employee2);
        employee2.addPaySlip(paySlip3);

        paySlip4.setEmployee(employee2);
        employee2.addPaySlip(paySlip4);

        paySlip5.setEmployee(employee3);
        employee3.addPaySlip(paySlip5);

        paySlip6.setEmployee(employee3);
        employee3.addPaySlip(paySlip6);

        accessCard1.setEmployee(employee1);
        accessCard2.setEmployee(employee2);
        accessCard3.setEmployee(employee3);

        employee1.setAccessCard(accessCard1);
        employee2.setAccessCard(accessCard2);
        employee3.setAccessCard(accessCard3);

        EntityTransaction entityTransactionSelect = entityManager.getTransaction();
        entityTransactionSelect.begin();

        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.persist(employee3);

        entityManager.persist(accessCard1);
        entityManager.persist(accessCard2);
        entityManager.persist(accessCard3);

        entityManager.persist(paySlip1);
        entityManager.persist(paySlip2);
        entityManager.persist(paySlip3);
        entityManager.persist(paySlip4);
        entityManager.persist(paySlip5);
        entityManager.persist(paySlip6);

        entityManager.persist(team1);
        entityManager.persist(team2);

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
//        EntityTransaction entityTransactionDelete = entityManager.getTransaction();
//        entityTransactionDelete.begin();
//        Employee employeeToDelete = entityManager.find(Employee.class,3);
//        entityManager.remove(employeeToDelete);
//        entityTransactionDelete.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
