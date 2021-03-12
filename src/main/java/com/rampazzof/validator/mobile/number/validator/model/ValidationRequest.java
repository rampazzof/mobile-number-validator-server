package com.rampazzof.validator.mobile.number.validator.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ValidationRequest {
    @NotNull
    private String mobileNumber;
}
