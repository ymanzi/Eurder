package com.switchfully.eurder.item.service;

import com.switchfully.eurder.infrastructure.exceptions.NonExistentItemException;
import com.switchfully.eurder.infrastructure.exceptions.UnauthorizedException;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.ItemRepository;
import com.switchfully.eurder.service.mappers.ItemMapper;
import com.switchfully.eurder.service.ItemService;
import com.switchfully.eurder.service.dtos.CreateItemDto;
import com.switchfully.eurder.service.dtos.ItemDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

class ItemServiceTest {
    private final ItemMapper itemMapper = new ItemMapper();
    private final ItemRepository itemRepository = new ItemRepository();
    private final ItemService itemService = new ItemService(itemMapper, itemRepository);

    private final CreateItemDto testItemDto = new CreateItemDto("name", "description", 10, 45);
    private final Item testItem = new Item("name", "description", 10, 45);


    @Test
    void save_whenTryingToSaveAsAdmin_thenReturnGivenItem() {
        ItemDto savedItemDto = itemService.save(testItemDto, "admin");

        Assertions.assertThat(savedItemDto.getName()).isEqualTo(testItemDto.name() );
    }

    @Test
    void save_whenTryingToSaveAsNonAdmin_thenThrowUnauthorizedException() {
        org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedException.class, () -> itemService.save(testItemDto, "123"));
    }

    @Test
    void update_whenUpdatingAnExistingItemAsAdmin_thenReturnUpdatedItem() {
        //Given
        itemRepository.save(testItem);

        //When
        CreateItemDto toUpdateItemDto = new CreateItemDto("updated", "updated", 500, 8);
        ItemDto returnedItemDto = itemService.update(testItem.getId(), toUpdateItemDto, "admin");

        //Then
        ItemDto updatedItemDto = new ItemDto(testItem.getId(), "updated", "updated", 500);
        Assertions.assertThat(returnedItemDto).isEqualTo(updatedItemDto);
    }

    @Test
    void update_whenUpdatingAnNonExistingItem_thenThrowNonExistentItemException() {

        //Then
        CreateItemDto toUpdateItemDto = new CreateItemDto("updated", "updated", 500, 8);
        org.junit.jupiter.api.Assertions.assertThrows(NonExistentItemException.class, () -> itemService.update(UUID.randomUUID(), toUpdateItemDto, "admin"));
    }

    @Test
    void update_whenUpdatingAsANonAdmin_thenThrowUnauthorizedException() {

        //Then
        CreateItemDto toUpdateItemDto = new CreateItemDto("updated", "updated", 500, 8);
        org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedException.class, () -> itemService.update(testItem.getId(), toUpdateItemDto, "123"));
    }

    @Test
    void getAll_AsANonAdmin_thenThrowUnauthorizedException() {

        //Then
        org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedException.class, () -> itemService.getAll("123"));
    }

    @Test
    void getAll_givenAPopulatedRepository_thenRetrieveAllItemSortedByStockAmount() {
        //Given
        Item item1 = new Item("name", "description", 1.6, 30);
        Item item2 = new Item("name", "description", 1.6, 10);
        itemRepository.save(testItem);
        itemRepository.save(item1);
        itemRepository.save(item2);



        //When
        List<ItemDto> listOfItemDto = itemService.getAll("admin");

        //Then
        Assertions.assertThat(listOfItemDto).containsExactlyElementsOf(itemMapper.toDto(List.of(item2, item1, testItem)));
    }

    @Test
    void getLow_asAdmin_thenRetrieveAllLowStockItems() {
        //Given
        Item item1 = new Item("low", "description", 1.6, 0);
        Item item2 = new Item("low", "description", 1.6, 3);
        Item item3 = new Item("medium", "description", 1.6, 5);
        Item item4 = new Item("high", "description", 1.6, 10);
        Item item5 = new Item("high", "description", 1.6, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);

        //When
        List<ItemDto> listOfItemDto = itemService.getLow("admin");

        //Then
        listOfItemDto.stream().forEach(itemDto -> itemDto.getName().equals("low"));
    }

    @Test
    void getMedium_asAdmin_thenRetrieveAllMediumStockItems() {
        //Given
        Item item1 = new Item("low", "description", 1.6, 0);
        Item item2 = new Item("low", "description", 1.6, 3);
        Item item3 = new Item("medium", "description", 1.6, 5);
        Item item4 = new Item("high", "description", 1.6, 10);
        Item item5 = new Item("high", "description", 1.6, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);

        //When
        List<ItemDto> listOfItemDto = itemService.getMedium("admin");

        //Then
        listOfItemDto.stream().forEach(itemDto -> itemDto.getName().equals("medium"));
    }

    @Test
    void getHigh_asAdmin_thenRetrieveAllHighStockItems() {
        //Given
        Item item1 = new Item("low", "description", 1.6, 0);
        Item item2 = new Item("low", "description", 1.6, 3);
        Item item3 = new Item("medium", "description", 1.6, 5);
        Item item4 = new Item("high", "description", 1.6, 10);
        Item item5 = new Item("high", "description", 1.6, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);

        //When
        List<ItemDto> listOfItemDto = itemService.getHigh("admin");

        //Then
        listOfItemDto.stream().forEach(itemDto -> itemDto.getName().equals("high"));
    }

    @Test
    void getLow_AsANonAdmin_thenThrowUnauthorizedException() {

        //Then
        org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedException.class, () -> itemService.getLow("123"));
    }

    @Test
    void getMedium_AsANonAdmin_thenThrowUnauthorizedException() {

        //Then
        org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedException.class, () -> itemService.getMedium("123"));
    }

    @Test
    void getHigh_AsANonAdmin_thenThrowUnauthorizedException() {

        //Then
        org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedException.class, () -> itemService.getHigh("123"));
    }
}