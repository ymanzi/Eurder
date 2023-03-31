package com.switchfully.eurder.item.service;

import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.service.dto.CreateItemDto;
import com.switchfully.eurder.item.service.dto.ItemDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ItemMapper {
    public Item fromDto(CreateItemDto createItemDto) {
        return new Item(createItemDto.name(), createItemDto.description(), createItemDto.price(), createItemDto.stock());
    }

    public Item fromDto(UUID itemId, CreateItemDto createItemDto){
        return new Item(itemId, createItemDto.name(), createItemDto.description(), createItemDto.price(), createItemDto.stock());
    }

    public ItemDto toDto(Item item){
        return new ItemDto(item.getId(), item.getName(), item.getDescription(), item.getPrice());
    }

    public List<ItemDto> toDto(List<Item> listOfItems){
        return listOfItems
                .stream()
                .map(this::toDto)
                .toList();
    }
}
