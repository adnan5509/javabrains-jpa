package model;

import enums.EmployeeType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;

    private int age;

    @Temporal(TemporalType.DATE)
    private Date dob;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @OneToOne
    private AccessCard accessCard;

    @OneToMany(mappedBy = "employee")
    private List<PaySlip> paySlips = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "EMPLOYEE_TEAM_MEMBERSHIPS",
            joinColumns = @JoinColumn(name = "EMPLOYEE_ID"),
            inverseJoinColumns = @JoinColumn(name = "TEAM_ID")
    )
    private List<Team> employeeTeams = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(final Date dob) {
        this.dob = dob;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(final EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public AccessCard getAccessCard() {
        return accessCard;
    }

    public void setAccessCard(final AccessCard accessCard) {
        this.accessCard = accessCard;
    }

    public List<PaySlip> getPaySlips() {
        return paySlips;
    }

    public void setPaySlips(final List<PaySlip> paySlips) {
        this.paySlips = paySlips;
    }

    public List<Team> getEmployeeTeams() {
        return employeeTeams;
    }

    public void setEmployeeTeams(final List<Team> employeeTeams) {
        this.employeeTeams = employeeTeams;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dob=" + dob +
                ", employeeType=" + employeeType +
                '}';
    }

    public void addPaySlip(PaySlip paySlip) {
        this.paySlips.add(paySlip);
    }

    public void assignTeamToEmployee(Team team) {
        this.employeeTeams.add(team);
    }
}
