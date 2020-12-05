package com.Kotech.Customer.Repositories;

import com.Kotech.Customer.Entities.Customers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;



public interface CustomersRepository extends JpaRepository<Customers,Long> {

    @Query("SELECT c from Customers c Where lower(c.firstname)=: firstname")
    Optional<Customers> findCustomersByFirstName(String firstname);

    @Query("SELECT c FROM Customers c WHERE lower(c.lastname)" +
            "LIKE :#{#lastname == null || #lastname.isEmpty()? '%' : '%'+#lastname+'%'}")
    Page<Customers> findPageCustomers(Pageable page, String lastname);

    Customers findCustomerById(Long id);
}
