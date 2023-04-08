package com.JBProject3.serviceCustomer;

import com.JBProject3.modelCustomer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCustomer extends JpaRepository<Customer,Integer> {
}
