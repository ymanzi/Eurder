package com.switchfully.eurder.item.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ItemRepositoryTest {

    private ItemRepository itemRepository;
    private Item itemForTest = new Item("name", "description", 1.6, 3);

    @BeforeEach
    void setUp() {
        itemRepository = new ItemRepository();
    }

    @Test
    void save_whenSavingAnItem_thenReturnGivenItem() {
        Item savedItem = itemRepository.save(itemForTest);
        Assertions.assertThat(savedItem).isEqualTo(itemForTest);
    }
}