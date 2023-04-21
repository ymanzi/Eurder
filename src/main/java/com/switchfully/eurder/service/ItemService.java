package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.classes.Item;
import com.switchfully.eurder.domain.ItemRepository;
import com.switchfully.eurder.domain.classes.Supply;
import com.switchfully.eurder.infrastructure.exceptions.NonExistentItemException;
import com.switchfully.eurder.service.dtos.CreateItemDto;
import com.switchfully.eurder.service.dtos.ItemDto;
import com.switchfully.eurder.service.mappers.ItemMapper;
import com.switchfully.eurder.infrastructure.Utils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
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

    public ItemDto update(int itemId, CreateItemDto createItemDto, String adminId){
        Utils.adminAccess(adminId);

        Item item = itemRepository
                .findById(itemId)
                .orElseThrow(() -> new NonExistentItemException("id"));

        item.setName(createItemDto.name());
        item.setDescription(createItemDto.description());
        item.setPrice(createItemDto.price());
        item.setStockAmount(createItemDto.stock());
        return itemMapper.toDto(item);
    }

    public List<ItemDto> getAll(String adminId) {
        Utils.adminAccess(adminId);

        List<Item> listOfItems = itemRepository.findByOrderByStockAmountAsc();
        return itemMapper.toDto(listOfItems);
    }

    public List<ItemDto> getLow(String adminId) {
        Utils.adminAccess(adminId);

        List<Item> listOfItems = itemRepository.findAllBySupply(Supply.STOCK_LOW);
        return itemMapper.toDto(listOfItems);
    }

    public List<ItemDto> getMedium(String adminId) {
        Utils.adminAccess(adminId);

        List<Item> listOfItems = itemRepository.findAllBySupply(Supply.STOCK_MEDIUM);
        return itemMapper.toDto(listOfItems);
    }

    public List<ItemDto> getHigh(String adminId) {
        Utils.adminAccess(adminId);

        List<Item> listOfItems = itemRepository.findAllBySupply(Supply.STOCK_HIGH);
        return itemMapper.toDto(listOfItems);
    }
}
