package com.rampazzof.validator.mobile.number.validator.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "mobile_number")
public class MobileNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String mobileNumber;
    private String rawMobileNumber;
    private Status status;

    public enum Status {
        VALID,
        NOT_VALID,
        CORRECTED
    }
}
