package com.switchfully.eurder.domain;

import com.switchfully.eurder.domain.classes.Customer;
import com.switchfully.eurder.domain.classes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
