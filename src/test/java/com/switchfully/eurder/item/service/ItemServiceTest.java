package com.switchfully.eurder.item.service;

import com.switchfully.eurder.exceptions.UnauthorizedException;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.domain.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemServiceTest {
    private final ItemMapper itemMapper = new ItemMapper();
    private final ItemRepository itemRepository = new ItemRepository();
    private final ItemService itemService = new ItemService(itemMapper, itemRepository);

    private CreateItemDto testItemDto = new CreateItemDto("name", "description", 10, 45);
    private Item testItem = new Item("name", "description", 10, 45);


    @Test
    void save_whenTryingToSaveAsAdmin_thenReturnGivenItem() {
        ItemDto savedItemDto = itemService.save(testItemDto, "admin");

        Assertions.assertThat(savedItemDto.getName()).isEqualTo(testItemDto.name() );
    }

    @Test
    void save_whenTryingToSaveAsNonAdmin_thenThrowUnauthorizedException() {
        org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedException.class, () -> itemService.save(testItemDto, "123"));
    }
}