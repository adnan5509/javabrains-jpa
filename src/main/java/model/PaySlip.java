package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import java.util.Date;

@Entity
@NamedQuery(name = "employeeTotalEarnings", query = "SELECT SUM (p.salary) FROM PaySlip p GROUP BY p.employee.id HAVING p.employee.id = :givenEmployee")
public class PaySlip {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private Date periodStartDate;
    private Date periodEndDate;
    private double salary;
    @ManyToOne
    @JoinColumn(name = "payslip_employee")
    private Employee employee;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Date getPeriodStartDate() {
        return periodStartDate;
    }

    public void setPeriodStartDate(final Date periodStartDate) {
        this.periodStartDate = periodStartDate;
    }

    public Date getPeriodEndDate() {
        return periodEndDate;
    }

    public void setPeriodEndDate(final Date periodEndDate) {
        this.periodEndDate = periodEndDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(final double salary) {
        this.salary = salary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(final Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "PaySlip{" +
                "id=" + id +
                ", periodStartDate=" + periodStartDate +
                ", periodEndDate=" + periodEndDate +
                ", salary=" + salary +
                ", employee=" + employee +
                '}';
    }
}
