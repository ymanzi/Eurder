package com.switchfully.eurder.utils;

import com.switchfully.eurder.exceptions.MandatoryFieldException;
import com.switchfully.eurder.exceptions.UnauthorizedException;

public class Utils {
    public static void fieldIsPresent(String field, String type) throws MandatoryFieldException {
        if (field == null || field.isEmpty() || field.isBlank())
            throw new MandatoryFieldException(type);
    }

    public static void adminAccess(String adminId){
        if (!adminId.equals("admin")){
            throw new UnauthorizedException();
        }
    }
}
