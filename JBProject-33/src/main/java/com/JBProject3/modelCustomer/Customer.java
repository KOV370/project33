package com.JBProject3.modelCustomer;

import jakarta.persistence.*;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@Entity
@Table(name = "table_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "first_name", nullable = false)
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "discount_customer")
    private double discount_customer;
    @Column(name = "day_birth")
    private Date day_birth;


    public Customer(int id, String first_name, String last_name, String login,
                    String password, double discount_customer, Date day_birth) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
        this.discount_customer = discount_customer;
        this.day_birth = day_birth;
    }

    public Customer() {
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", discount_customer=" + discount_customer +
                ", day_birth=" + day_birth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (Double.compare(customer.discount_customer, discount_customer) != 0) return false;
        if (first_name != null ? !first_name.equals(customer.first_name) : customer.first_name != null) return false;
        if (last_name != null ? !last_name.equals(customer.last_name) : customer.last_name != null) return false;
        if (login != null ? !login.equals(customer.login) : customer.login != null) return false;
        if (password != null ? !password.equals(customer.password) : customer.password != null) return false;
        return day_birth != null ? day_birth.equals(customer.day_birth) : customer.day_birth == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        temp = Double.doubleToLongBits(discount_customer);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (day_birth != null ? day_birth.hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public double getDiscount_customer() {
        return discount_customer;
    }

    public void setDiscount_customer(double discount_customer) {
        this.discount_customer = discount_customer;
    }

    public Date getDay_birth() {
        return day_birth;
    }

    public void setDay_birth(Date day_birth) {
        this.day_birth = day_birth;
    }
}
