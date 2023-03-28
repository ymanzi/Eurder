package com.switchfully.eurder.item.domain;

import com.switchfully.eurder.exceptions.MandatoryFieldException;
import com.switchfully.eurder.exceptions.NegativeNumberInputException;
import org.junit.jupiter.api.Assertions;

class ItemTest {

    void constructor_whenCreatingAnItemWithoutName_thenThrowMandatoryFieldException(){
        Assertions.assertThrows(MandatoryFieldException.class, () -> new Item("", "description", 1, 1));
    }

    void constructor_whenCreatingAnItemWithNegativePrice_thenThrowNegativeNumberInputException(){
        Assertions.assertThrows(NegativeNumberInputException.class, () -> new Item("name", "description", -1, 1));
    }

    void constructor_whenCreatingAnItemWithNegativeStock_thenThrowNegativeNumberInputException(){
        Assertions.assertThrows(NegativeNumberInputException.class, () -> new Item("name", "description", 1, -1));
    }
}