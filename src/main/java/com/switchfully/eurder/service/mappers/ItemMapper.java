package com.switchfully.eurder.service.mappers;

import com.switchfully.eurder.domain.classes.Item;
import com.switchfully.eurder.service.dtos.CreateItemDto;
import com.switchfully.eurder.service.dtos.ItemDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ItemMapper {
    public Item fromDto(CreateItemDto createItemDto) {
        return new Item(createItemDto.name(), createItemDto.description(), createItemDto.price(), createItemDto.stock());
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
