package com.switchfully.eurder.item.service.dto;

import com.switchfully.eurder.item.domain.Stock;

import java.util.Objects;
import java.util.UUID;

public class ItemDto {
    private final UUID id;
    private final String name;
    private final String description;
    private final double price;

    public ItemDto(UUID id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDto itemDto = (ItemDto) o;
        return Double.compare(itemDto.price, price) == 0 && Objects.equals(id, itemDto.id) && Objects.equals(name, itemDto.name) && Objects.equals(description, itemDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price);
    }
}
