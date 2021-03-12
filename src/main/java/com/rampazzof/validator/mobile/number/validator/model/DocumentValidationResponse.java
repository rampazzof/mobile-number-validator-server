package com.rampazzof.validator.mobile.number.validator.model;

import lombok.Data;

import java.util.List;

@Data
public class DocumentValidationResponse {
    private List<ValidationResponse> validationResponseList;
}
