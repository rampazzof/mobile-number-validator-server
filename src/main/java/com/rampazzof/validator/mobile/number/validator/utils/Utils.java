package com.rampazzof.validator.mobile.number.validator.utils;

import java.util.regex.Pattern;

public class Utils {
    public static String filterNumbers(String mobileNumber) {
        return mobileNumber.replaceAll("[^0-9]+", "");
    }

    public static boolean isAllNumbers(String mobileNumber) {
        if (mobileNumber == null) {
            return false;
        }
        var pattern = Pattern.compile("[0-9]+");
        return pattern.matcher(mobileNumber).matches();
    }
}
