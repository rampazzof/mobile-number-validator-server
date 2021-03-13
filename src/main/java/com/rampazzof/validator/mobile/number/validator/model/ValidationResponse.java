package com.rampazzof.validator.mobile.number.validator.model;

import com.rampazzof.validator.mobile.number.validator.dto.MobileNumber;
import lombok.Data;

@Data
public class ValidationResponse {
    private String rawMobileNumber;
    private String mobileNumber;
    private String status;

    public ValidationResponse mapValidationResponse(MobileNumber mobileNumber) {
        var validationResponse = new ValidationResponse();
        validationResponse.setRawMobileNumber(mobileNumber.getRawMobileNumber());
        validationResponse.setMobileNumber(mobileNumber.getMobileNumber());
        validationResponse.setStatus(mobileNumber.getStatus().name());
        return validationResponse;
    }
}
