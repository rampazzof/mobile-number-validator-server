package com.rampazzof.validator.mobile.number.validator.model;

import lombok.Data;

@Data
public class ValidationResponse {
    private String rawMobileNumber;
    private String mobileNumber;
    private String status;
}
