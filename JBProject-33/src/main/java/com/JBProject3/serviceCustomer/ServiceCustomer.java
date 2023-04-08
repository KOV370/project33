package com.JBProject3.serviceCustomer;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.JBProject3.controllerCustomer.ControllerCustomer;
import com.JBProject3.modelCustomer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;

@Service
public class ServiceCustomer {
    @Autowired
    private RepositoryCustomer repositoryCustomer;
    private ControllerCustomer controllerCustomer;
    private Customer customer = new Customer();

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
}
