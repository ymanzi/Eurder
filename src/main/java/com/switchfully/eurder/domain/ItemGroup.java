package com.switchfully.eurder.domain;

import com.switchfully.eurder.domain.Item;

import java.time.LocalDate;

public class ItemGroup {
    private Item item;
    private int amount;
    private LocalDate shippingDate;


    public ItemGroup(Item item, int amount) {
        this.item = item;
        this.amount = amount;
        this.shippingDate = setShippingDate(amount, item.getStockAmount());
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
