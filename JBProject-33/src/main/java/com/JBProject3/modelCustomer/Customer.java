package com.JBProject3.modelCustomer;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@Table(name = "table_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "First Name should not be empty")
    @Size(min =1, max = 6, message ="First Name should not be between 1 and 6" )
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Last Name should not be empty")
    @Size(min =1, max = 2, message ="Last Name should not be between 1 and 2" )
    private String lastName;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "discount_customer")
    @Max(value = 10, message = "Discount should not be more 10%")
    @Min(value = 0,message = "Discount should not be less 0")
    private double discountCustomer;

    @Column(name = "day_birth")
    @DateTimeFormat(fallbackPatterns = "yyyy")
    private Date dayBirth;

    public Customer(int id, String firstName, String lastName, String login,
                    String password, double discountCustomer, Date dayBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.discountCustomer = discountCustomer;
        this.dayBirth = dayBirth;
    }

    public Customer() {
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", discount_customer=" + discountCustomer +
                ", day_birth=" + dayBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (Double.compare(customer.discountCustomer, discountCustomer) != 0) return false;
        if (firstName != null ? !firstName.equals(customer.firstName) : customer.firstName != null) return false;
        if (lastName != null ? !lastName.equals(customer.lastName) : customer.lastName != null) return false;
        if (login != null ? !login.equals(customer.login) : customer.login != null) return false;
        if (password != null ? !password.equals(customer.password) : customer.password != null) return false;
        return dayBirth != null ? dayBirth.equals(customer.dayBirth) : customer.dayBirth == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        temp = Double.doubleToLongBits(discountCustomer);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (dayBirth != null ? dayBirth.hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getDiscountCustomer() {
        return discountCustomer;
    }

    public void setDiscountCustomer(double discountCustomer) {
        this.discountCustomer = discountCustomer;
    }

    public Date getDayBirth() {
        return dayBirth;
    }

    public void setDayBirth(Date dayBirth) {
        this.dayBirth = dayBirth;
    }
}










