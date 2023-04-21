package com.switchfully.eurder.domain;

import com.switchfully.eurder.domain.classes.Item;
import com.switchfully.eurder.domain.classes.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    public List<Item> findByOrderByStockAmountAsc();
    public List<Item> findAllBySupply(Supply supply);
}
