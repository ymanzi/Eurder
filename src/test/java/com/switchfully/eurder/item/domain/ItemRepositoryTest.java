package com.switchfully.eurder.item.domain;

import com.switchfully.eurder.exceptions.NonExistentItemException;
import com.switchfully.eurder.item.service.ItemMapper;
import com.switchfully.eurder.item.service.dto.ItemDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class ItemRepositoryTest {

    private ItemRepository itemRepository;
    private final ItemMapper itemMapper = new ItemMapper();

    private final Item itemForTest = new Item("name", "description", 1.6, 3);

    @BeforeEach
    void setUp() {
        itemRepository = new ItemRepository();
    }

    @Test
    void save_whenSavingAnItem_thenReturnGivenItem() {
        Item savedItem = itemRepository.save(itemForTest);
        Assertions.assertThat(savedItem).isEqualTo(itemForTest);
    }

    @Test
    void update_whenUpdatingAnExistingItem_thenReturnUpdatedItem() {
        //Given
        itemRepository.save(itemForTest);

        //When
        ItemDto updatedItemDto = new ItemDto(itemForTest.getId(), "updated", "updated", 500, 8);
        Item updatedItem = itemMapper.fromDto(updatedItemDto);
        Item returnedItem = itemRepository.update(updatedItem);

        //Then
        Assertions.assertThat(returnedItem).isEqualTo(updatedItem);
    }

    @Test
    void update_whenUpdatingAnNonExistingItem_thenThrowNonExistentItemException() {

        //Then
        org.junit.jupiter.api.Assertions.assertThrows(NonExistentItemException.class, () -> itemRepository.update(itemForTest));
    }

    @Test
    void getAllOrderedBySupply_givenAPopulatedRepository_thenRetrieveAllItemSortedByStockAmount() {
        //Given
        Item item1 = new Item("name", "description", 1.6, 30);
        Item item2 = new Item("name", "description", 1.6, 10);
        itemRepository.save(itemForTest);
        itemRepository.save(item1);
        itemRepository.save(item2);

        //When
        List<Item> listOfItem = itemRepository.getAllOrderedBySupply();

        //Then
        Assertions.assertThat(listOfItem).containsExactlyElementsOf(List.of(itemForTest, item2, item1));
    }


}