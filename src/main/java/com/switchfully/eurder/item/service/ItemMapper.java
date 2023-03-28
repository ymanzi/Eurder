package com.switchfully.eurder.item.service;

import com.switchfully.eurder.item.domain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item fromDto(CreateItemDto createItemDto) {
        return new Item(createItemDto.name(), createItemDto.description(), createItemDto.price(), createItemDto.stock());
    }

    public ItemDto toDto(Item item){
        return new ItemDto(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getStock());
    }
}
