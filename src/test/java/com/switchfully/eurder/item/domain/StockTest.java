package com.switchfully.eurder.item.domain;

import com.switchfully.eurder.exceptions.NegativeNumberInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    @Test
    void constructor_whenProvidingNegativeStockAmount_thenThrowNegativeNumberInputException(){
        Assertions.assertThrows(NegativeNumberInputException.class, ()-> new Stock(-5));
    }

    @Test
    void getSupply_providingThreeStockAmount_thenReturnLowStockSupply() {
        Stock stock = new Stock(3);
        assertEquals(Supply.STOCK_LOW, stock.getSupply());
    }

    @Test
    void getSupply_providingEightStockAmount_thenReturnMediumStockSupply() {
        Stock stock = new Stock(8);
        assertEquals(Supply.STOCK_MEDIUM, stock.getSupply());
    }

    @Test
    void getSupply_providingTenStockAmount_thenReturnHighStockSupply() {
        Stock stock = new Stock(10);
        assertEquals(Supply.STOCK_HIGH, stock.getSupply());
    }

    @Test
    void getSupply_providingTwelveStockAmount_thenReturnHighStockSupply() {
        Stock stock = new Stock(12);
        assertEquals(Supply.STOCK_HIGH, stock.getSupply());
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}