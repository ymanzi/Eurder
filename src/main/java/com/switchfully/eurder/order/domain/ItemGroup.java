package com.switchfully.eurder.order.domain;

import com.switchfully.eurder.item.domain.Item;

import java.time.LocalDate;

public class ItemGroup {
    private final Item item;
    private int amount;
    private final LocalDate shippingDate;

    public ItemGroup(Item item, int amount) {
        this.item = item;
        this.amount = amount;
        this.shippingDate = setShippingDate(amount, item.getStock());
    }

    private LocalDate setShippingDate(int amount, int stock){
        if (amount <= stock) {
            return LocalDate.now().plusDays(1);
        }
        return LocalDate.now().plusDays(7);
    }

    public double getPrice(){
        return amount * item.getPrice();
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }
}
