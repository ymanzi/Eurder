package com.switchfully.eurder.domain;

import com.switchfully.eurder.domain.classes.Customer;
import com.switchfully.eurder.domain.classes.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    public List<Order> findByCustomer(Customer customer);

}
