package com.JBProject3.serviceCustomer;

import com.JBProject3.modelCustomer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RepositoryCustomer extends JpaRepository<Customer,Integer> {
    List<Customer> findByDayBirthBetween(Date dateStart, Date dateFinish);
}
