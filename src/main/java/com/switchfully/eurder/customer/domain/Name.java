package com.switchfully.eurder.customer.domain;

import com.switchfully.eurder.utils.Utils;

public record Name(String first, String last) {
    public Name {
        Utils.fieldIsPresent(first, "firstname");
        Utils.fieldIsPresent(last, "lastname");
    }
}
