package com.switchfully.eurder.item.api;

import com.switchfully.eurder.item.service.CreateItemDto;
import com.switchfully.eurder.item.service.ItemDto;
import com.switchfully.eurder.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
    private final ItemService itemService;
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }




    //=======================================  GET  ==================================================

    //=======================================  POST ==================================================
    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public ItemDto create(@RequestBody CreateItemDto createItemDto, @RequestHeader String adminId){
        return itemService.save(createItemDto, adminId);
    }
}
