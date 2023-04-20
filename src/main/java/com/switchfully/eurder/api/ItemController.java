package com.switchfully.eurder.api;

import com.switchfully.eurder.service.dtos.CreateItemDto;
import com.switchfully.eurder.service.dtos.ItemDto;
import com.switchfully.eurder.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
    private final ItemService itemService;
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    //=======================================  GET  ==================================================
    @GetMapping(path = "/" , produces = "application/json")
    public List<ItemDto> getAll(@RequestHeader String adminId){
        return itemService.getAll(adminId);
    }

    @GetMapping(path = "/stock/low" , produces = "application/json")
    public List<ItemDto> getLow(@RequestHeader String adminId){
        return itemService.getLow(adminId);
    }

    @GetMapping(path = "/stock/medium" , produces = "application/json")
    public List<ItemDto> getMedium(@RequestHeader String adminId){
        return itemService.getMedium(adminId);
    }


    @GetMapping(path = "/stock/high" , produces = "application/json")
    public List<ItemDto> getHigh(@RequestHeader String adminId){
        return itemService.getHigh(adminId);
    }


    //=======================================  POST ==================================================
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ItemDto create(@RequestBody CreateItemDto createItemDto, @RequestHeader String adminId){
        return itemService.save(createItemDto, adminId);
    }
    //=======================================  PUT  ==================================================
    @PutMapping(path="/{itemId}", consumes = "application/json", produces = "application/json")
    public ItemDto update(@PathVariable UUID itemId, @RequestBody CreateItemDto createItemDto, @RequestHeader String adminId){
        return itemService.update(itemId, createItemDto, adminId);
    }
}
