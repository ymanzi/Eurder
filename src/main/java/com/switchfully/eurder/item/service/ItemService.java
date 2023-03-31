package com.switchfully.eurder.item.service;

import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.domain.ItemRepository;
import com.switchfully.eurder.item.service.dto.CreateItemDto;
import com.switchfully.eurder.item.service.dto.ItemDto;
import com.switchfully.eurder.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemMapper itemMapper, ItemRepository itemRepository) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    public ItemDto save(CreateItemDto createItemDto, String adminId) {
        Utils.adminAccess(adminId);

        Item item = itemMapper.fromDto(createItemDto);
        Item savedItem = itemRepository.save(item);
        return itemMapper.toDto(savedItem);
    }

    public ItemDto update(UUID itemId, CreateItemDto createItemDto, String adminId){
        Utils.adminAccess(adminId);

        Item item = itemMapper.fromDto(itemId, createItemDto);
        Item updatedItem = itemRepository.update(item);
        return itemMapper.toDto(updatedItem);
    }

    public List<ItemDto> getAll(String adminId) {
        Utils.adminAccess(adminId);

        List<Item> listOfItems = itemRepository.getAllOrderedBySupply();
        return itemMapper.toDto(listOfItems);
    }

    public List<ItemDto> getLow(String adminId) {
        Utils.adminAccess(adminId);

        List<Item> listOfItems = itemRepository.getLowStock();
        return itemMapper.toDto(listOfItems);
    }

    public List<ItemDto> getMedium(String adminId) {
        Utils.adminAccess(adminId);

        List<Item> listOfItems = itemRepository.getMediumStock();
        return itemMapper.toDto(listOfItems);
    }

    public List<ItemDto> getHigh(String adminId) {
        Utils.adminAccess(adminId);

        List<Item> listOfItems = itemRepository.getHighStock();
        return itemMapper.toDto(listOfItems);
    }
}
