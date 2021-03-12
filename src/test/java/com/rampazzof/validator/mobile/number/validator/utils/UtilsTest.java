package com.rampazzof.validator.mobile.number.validator.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JUnit Utils Test")
public class UtilsTest {

    @Test
    @DisplayName("filter numbers regex test")
    public void filterNumbersTest() {
        assertEquals("27010203045", Utils.filterNumbers("27010203045"));
        assertEquals("27010203045", Utils.filterNumbers("27010abc203045"));
        assertEquals("27010203045", Utils.filterNumbers("acb27010203045abc"));
    }

    @Test
    @DisplayName("all numbers regex test")
    public void allNumbersTest() {
        assertTrue(Utils.isAllNumbers("27010203045"));
        assertFalse(Utils.isAllNumbers("27010abc203045"));
        assertFalse(Utils.isAllNumbers("abc27010203045abc"));
        assertFalse(Utils.isAllNumbers(""));
    }
}
