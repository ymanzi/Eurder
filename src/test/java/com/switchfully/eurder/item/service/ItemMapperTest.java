package com.switchfully.eurder.item.service;

import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.service.dto.CreateItemDto;
import com.switchfully.eurder.item.service.dto.ItemDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemMapperTest {
    private final ItemMapper itemMapper = new ItemMapper();

    @Test
    void fromDto_givenACreateItemDto_thenReturnCorrespondingItem() {
        //Given
        CreateItemDto createItemDto = new CreateItemDto("name", "description", 1.0, 5);
        //When
        Item item = itemMapper.fromDto(createItemDto);

        //Then
        assertEquals(createItemDto.name(), item.getName());
        assertEquals(createItemDto.description(), item.getDescription());
        assertEquals(createItemDto.price(), item.getPrice());
        assertEquals(createItemDto.stock(), item.getStockAmount());
    }

    @Test
    void toDto_givenAnItem_thenReturnCorrespondingItemDto() {
        //Given
        Item item = new Item("name", "description", 1.0, 5);
        //When
        ItemDto itemDto = itemMapper.toDto(item);

        //Then
        assertEquals(itemDto.getId(), item.getId());
        assertEquals(itemDto.getName(), item.getName());
        assertEquals(itemDto.getDescription(), item.getDescription());
        assertEquals(itemDto.getPrice(), item.getPrice());
        assertEquals(itemDto.getStockAmount(), item.getStockAmount());
    }

    @Test
    void toDto_givenAListOfItems_thenReturnListOfCorrespondingItemDto() {
        //Given
        Item item = new Item("name", "description", 1.0, 5);
        Item item2 = new Item("name", "description", 1.0, 5);
        List<Item> listOfItem = List.of(item, item2);

        //When
        List<ItemDto> listOfItemDto = itemMapper.toDto(listOfItem);

        for (int index=0; index < listOfItemDto.size(); index++){
            Item item3 = listOfItem.get(index);
            ItemDto itemDto3 = listOfItemDto.get(index);

            //Then
            assertEquals(itemDto3.getId(), item3.getId());
            assertEquals(itemDto3.getName(), item3.getName());
            assertEquals(itemDto3.getDescription(), item3.getDescription());
            assertEquals(itemDto3.getPrice(), item3.getPrice());
            assertEquals(itemDto3.getStockAmount(), item3.getStockAmount());
        }
    }
}