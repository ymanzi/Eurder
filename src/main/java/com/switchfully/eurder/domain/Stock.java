package com.switchfully.eurder.domain;

import com.switchfully.eurder.infrastructure.exceptions.NegativeNumberInputException;

import java.util.Objects;

public class Stock {
    private final int amount;
    private final Supply supply;

    public Stock(int amount) {
        if (amount < 0)
            throw new NegativeNumberInputException("stock amount");
        this.amount = amount;
        if (amount < 5)
        {
            supply = Supply.STOCK_LOW;
        } else if (amount < 10) {
            supply = Supply.STOCK_MEDIUM;
        }
        else {
            supply = Supply.STOCK_HIGH;
        }
    }
    public int getAmount() {
        return amount;
    }

    public Supply getSupply() {
        return supply;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return amount == stock.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
