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
    @Autowired
    private RepositoryCustomer repositoryCustomer;
//    private ControllerCustomer controllerCustomer;

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

    public List<Customer> findByBirthYear(java.sql.Date birthDay) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String birthYear = simpleDateFormat.format(birthDay);
        System.out.println("birthYear - " + birthYear);
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date finish = null;
        try {
            start = simpleDateFormat1.parse(birthYear + "-01-01");
            finish = simpleDateFormat1.parse(birthYear + "-12-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return repositoryCustomer.findByDayBirthBetween(start, finish);

    }

    public List<Customer> findByDiscountMoreThan(double discount) {
        return repositoryCustomer.findByDiscountCustomerGreaterThan(discount);
    }

    public Customer updateAllCustomer(int id, Customer customer) {
        Customer newCustomer = findByIdCustomers(id);
        newCustomer.setFirstName(customer.getFirstName());
        newCustomer.setLastName(customer.getLastName());
        newCustomer.setLogin(customer.getLogin());
        newCustomer.setPassword(customer.getPassword());
        newCustomer.setDiscountCustomer(customer.getDiscountCustomer());
        newCustomer.setDayBirth(customer.getDayBirth());
        return repositoryCustomer.save(newCustomer);
    }

    public Customer updatePartCustomer(int id, Customer updatingParameters) {
        Customer updatedCustomer;
        Optional<Customer> optionalCustomer = repositoryCustomer.findById(id);
        if (optionalCustomer.isEmpty())
            return null;
        else
            updatedCustomer = optionalCustomer.get();
        if (updatingParameters.getFirstName() != null)
            updatedCustomer.setFirstName(updatingParameters.getFirstName());
        if (updatingParameters.getLastName() != null)
            updatedCustomer.setLastName(updatingParameters.getLastName());
        if (updatingParameters.getLogin() != null)
            updatedCustomer.setLogin(updatingParameters.getLogin());
        if (updatingParameters.getPassword() != null)
            updatedCustomer.setPassword(updatingParameters.getPassword());
        if (updatingParameters.getDayBirth() != null)
            updatedCustomer.setDayBirth(updatingParameters.getDayBirth());
        repositoryCustomer.save(updatedCustomer);
        return updatedCustomer;
    }

    public Customer updateDiscount(int id, double discount) {
        Customer updatedCustomer;
        Optional<Customer> optionalCustomer = repositoryCustomer.findById(id);
        if (optionalCustomer.isEmpty())
            return null;
        else {
            updatedCustomer = optionalCustomer.get();
            updatedCustomer.setDiscountCustomer(discount);
            repositoryCustomer.save(updatedCustomer);
            return updatedCustomer;
        }

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
