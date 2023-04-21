package com.switchfully.eurder.domain;

import com.switchfully.eurder.domain.classes.Address;
import com.switchfully.eurder.domain.classes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
