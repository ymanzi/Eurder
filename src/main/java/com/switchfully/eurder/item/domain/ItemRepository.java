package com.switchfully.eurder.item.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.UUID;

@Repository
public class ItemRepository {

    private HashMap<UUID, Item> itemsById;

    public ItemRepository() {
        itemsById = new HashMap<>();
    }

    public Item save(Item item) {
        UUID itemId = item.getId();
        itemsById.put(itemId, item);
        return itemsById.get(itemId);
    }
}
