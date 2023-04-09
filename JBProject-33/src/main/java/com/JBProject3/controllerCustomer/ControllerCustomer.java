package com.JBProject3.controllerCustomer;

import com.JBProject3.modelCustomer.Customer;
import com.JBProject3.serviceCustomer.ServiceCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/table_customer")
public class ControllerCustomer {
    ServiceCustomer serviceCustomer;

    @Autowired
    public ControllerCustomer(ServiceCustomer serviceCustomer) {
        this.serviceCustomer = serviceCustomer;
    }

    @PostMapping(value = "/createCustomer")
    private ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        if (serviceCustomer.createNewCustomer(customer) != null)
            return new ResponseEntity<>(customer, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/findAllCustomers")
    private ResponseEntity<List<Customer>> findAllCustomers() {
        List<Customer> customerList;
        if ((customerList = serviceCustomer.findAllCustomers()) != null)
            return new ResponseEntity<>(customerList, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByIdCustomers")
    private ResponseEntity<Customer> findByIdCustomers(@RequestParam("id") int id) {
        Customer customer;
        if ((customer = serviceCustomer.findByIdCustomers(id)) != null)
            return new ResponseEntity<>(customer, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByLoginCustomer")
    private ResponseEntity<List<Customer>> findByLoginCustomer(String login) {
        List<Customer> customerList = serviceCustomer.findByLoginCustomer(login);
        if (customerList == null || customerList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(customerList, HttpStatus.OK);

    }

    @GetMapping("/findByBirthYear")
    private ResponseEntity<List<Customer>> findByBirthYear
            (@RequestParam(value = "day_birth") Date birthDay) {
        List<Customer> customerList = null;
        try {
            customerList = serviceCustomer.findByBirthYear(birthDay);
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if (customerList.isEmpty() || customerList == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCustomer")
    private ResponseEntity<?> deleteCustomer(@RequestParam(value = "id") int id) {
        boolean deleted = serviceCustomer.deleteCustomer(id);
        if (deleted)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


}
