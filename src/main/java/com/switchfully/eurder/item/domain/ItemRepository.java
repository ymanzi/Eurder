package com.switchfully.eurder.item.domain;

import com.switchfully.eurder.exceptions.NonExistentItemException;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class ItemRepository {

    private final HashMap<UUID, Item> itemsById;

    public ItemRepository() {
        itemsById = new HashMap<>();
    }


    public Item getById(UUID id){
        return itemsById.get(id);
     }

    public Item save(Item item) {
        UUID itemId = item.getId();
        itemsById.put(itemId, item);
        return itemsById.get(itemId);
    }

    public Item update(Item item) {
        UUID id =item.getId();
        if (!itemsById.containsKey(id))
            throw new NonExistentItemException("id");

        itemsById.put(id, item);
        return itemsById.get(id);
    }

    public List<Item> getAllOrderedBySupply() {
        return itemsById
                .values()
                .stream()
                .sorted(Comparator.comparing(Item::getStockAmount))
                .toList();
    }

    public List<Item> getLowStock() {
        return itemsById
                .values()
                .stream()
                .sorted(Comparator.comparing(Item::getStockAmount))
                .filter(item -> item.getStockSupply().equals(Supply.STOCK_LOW))
                .toList();
    }

    public List<Item> getMediumStock() {
        return itemsById
                .values()
                .stream()
                .sorted(Comparator.comparing(Item::getStockAmount))
                .filter(item -> item.getStockSupply().equals(Supply.STOCK_MEDIUM))
                .toList();
    }

    public List<Item> getHighStock() {
        return itemsById
                .values()
                .stream()
                .sorted(Comparator.comparing(Item::getStockAmount))
                .filter(item -> item.getStockSupply().equals(Supply.STOCK_HIGH))
                .toList();
    }
}
