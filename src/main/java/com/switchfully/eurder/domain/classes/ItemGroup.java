package com.switchfully.eurder.domain.classes;

import jakarta.validation.constraints.Positive;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class ItemGroup {
    @NotNull
    private Item item;
    @Positive(message = "The amount ordered should be zero or positive !")
    private int amount;
    private Date shippingDate;


    public ItemGroup(Item item, int amount) {
        this.item = item;
        this.amount = amount;
        this.shippingDate = setShippingDate(amount, item.getStockAmount());
    }

    private Date setShippingDate(int amount, int stock){
        Instant now = Instant.now();
        int days = amount <= stock ? 1 : 7;
        return Date.from(now.plus((Duration.ofDays(days))));
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


    public Date getShippingDate() {
        return shippingDate;
    }
}
