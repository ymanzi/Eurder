package com.switchfully.eurder.domain;

import com.switchfully.eurder.infrastructure.Utils;

public record Name(String first, String last) {
    public Name {
        Utils.fieldIsPresent(first, "firstname");
        Utils.fieldIsPresent(last, "lastname");
    }
}
