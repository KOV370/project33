package com.JBProject3.serviceCustomer;

import com.JBProject3.controllerCustomer.ControllerCustomer;
import com.JBProject3.modelCustomer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCustomer {
    List<Customer> customerList;
    @Autowired
    private RepositoryCustomer repositoryCustomer;
    private ControllerCustomer controllerCustomer;
//    private Customer customer = new Customer();

    public Customer createNewCustomer(Customer customer) {
        return repositoryCustomer.save(customer);
    }

    public List<Customer> findAllCustomers() {
        return repositoryCustomer.findAll();
    }

    public Customer findByIdCustomers(int id) {
        Optional<Customer> optionalCustomer = repositoryCustomer.findById(id);
        return optionalCustomer.orElse(null);
    }

    public List<Customer> findByLoginCustomer(String login) {
        Customer customer = new Customer();
        customer.setLogin(login);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("login", p -> p.ignoreCase().equals(login))
                .withIgnorePaths("id", "first_name", "last_name", "password", "discount_customer", "day_birth");
        return repositoryCustomer.findAll(Example.of(customer, matcher));
    }

    public List<Customer> findByBirthYear(java.sql.Date birthDay) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String birthYear = simpleDateFormat.format(birthDay);
        System.out.println("birthYear - " + birthYear);
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date start = simpleDateFormat1.parse(birthYear + "-01-01");
        Date finish = simpleDateFormat1.parse(birthYear + "-12-31");
        return repositoryCustomer.findByDayBirthBetween(start, finish);

    }

    public boolean deleteCustomer(int id) {
        Optional<Customer> customer = repositoryCustomer.findById(id);
        if (customer.isEmpty())
            return false;
        else {
            repositoryCustomer.delete(customer.get());
            return true;
        }
    }
}
