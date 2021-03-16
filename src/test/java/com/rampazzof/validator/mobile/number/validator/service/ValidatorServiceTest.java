package com.rampazzof.validator.mobile.number.validator.service;

import com.rampazzof.validator.mobile.number.validator.repository.MobileNumberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("JUnit Validator Service Test")
public class ValidatorServiceTest {

    @Mock
    private MobileNumberRepository mobileNumberRepository;

    @InjectMocks
    private ValidatorService validatorService;

    @Test
    @DisplayName("check mobile number validity")
    public void isValidTest() {
        assertTrue(validatorService.isValid("27010203045"));
        assertFalse(validatorService.isValid("270102030450"));
        assertFalse(validatorService.isValid("278"));
        assertFalse(validatorService.isValid("01010203045"));
    }

    @Test
    @DisplayName("check cleaning mobile number")
    public void cleanNumberTest() {
        assertEquals("27010203045", validatorService.cleanNumber("27010203045"));
        assertEquals("27010203045", validatorService.cleanNumber("270102030450"));
        assertEquals("27010203045", validatorService.cleanNumber("027010203045"));
        assertEquals("", validatorService.cleanNumber("270102"));
        assertEquals("", validatorService.cleanNumber("0102132"));
    }
}
