package com.switchfully.eurder.customer.domain;

import com.switchfully.eurder.utils.Utils;

public record Name(String first, String last) {
    public Name(String first, String last) {
        Utils.fieldIsPresent(first, "firstname");
        Utils.fieldIsPresent(last, "lastname");
        this.first = first;
        this.last = last;
    }
}
