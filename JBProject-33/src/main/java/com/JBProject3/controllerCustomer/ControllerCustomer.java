package com.JBProject3.controllerCustomer;

import com.JBProject3.modelCustomer.Customer;
import com.JBProject3.serviceCustomer.ServiceCustomer;
import jakarta.validation.Valid;
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
    private ResponseEntity<Customer> createCustomer(@RequestBody @Valid Customer customer) {
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
        List<Customer> customerList;
        customerList = serviceCustomer.findByBirthYear(birthDay);
        if (customerList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping("/findByDiscountMore")
    private ResponseEntity<List<Customer>> findByDiscountMore(@RequestParam("discount_customer") @Valid double discount) {
        List<Customer> customerList = serviceCustomer.findByDiscountMoreThan(discount);
        if (customerList.isEmpty())
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

    @PutMapping("/updateAllCustomer")
    private ResponseEntity<Customer> updateAll(@RequestParam(value = "id") int id, @RequestBody @Valid Customer customer) {
        Customer savedCustomer = serviceCustomer.updateAllCustomer(id, customer);
        if (savedCustomer == null)
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        else return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }

    @PatchMapping("/updateDiscountCustomer")
    private ResponseEntity<Customer> updateDiscount(@RequestParam(value = "id") int id,
                                                    @RequestParam(value = "discount_customer") @Valid double discount) {
        Customer updatedCustomer = serviceCustomer.updateDiscount(id, discount);
        if (updatedCustomer == null)
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        else return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @PatchMapping("/updatePartCustomer")
    private ResponseEntity<Customer> updatePartCustomer(@RequestParam(value = "id") @Valid int id, @RequestBody Customer customer) {
        Customer updatedCustomer = serviceCustomer.updatePartCustomer(id, customer);
        if (customer == null)
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        else
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }


}
