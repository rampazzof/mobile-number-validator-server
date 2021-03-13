package com.rampazzof.validator.mobile.number.validator.service;

import com.rampazzof.validator.mobile.number.validator.dto.MobileNumber;
import com.rampazzof.validator.mobile.number.validator.repository.MobileNumberRepository;
import com.rampazzof.validator.mobile.number.validator.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidatorService {

    private final MobileNumberRepository mobileNumberRepository;

    public MobileNumber validate(String rawMobileNumber, boolean strong) {
        var optionalMobileNumber = mobileNumberRepository.findByRawMobileNumber(rawMobileNumber);
        if (optionalMobileNumber.isPresent()) {
            return optionalMobileNumber.get();
        }
        var mobileNumber = new MobileNumber();
        mobileNumber.setRawMobileNumber(rawMobileNumber);
        if (isValid(rawMobileNumber)) {
            mobileNumber.setMobileNumber(rawMobileNumber);
            mobileNumber.setStatus(MobileNumber.Status.VALID);
            return mobileNumberRepository.save(mobileNumber);
        }
        var mobileNumberCleaned = strong ? Utils.filterNumbers(rawMobileNumber) : cleanNumber(rawMobileNumber);
        if (isValid(mobileNumberCleaned)) {
            mobileNumber.setMobileNumber(mobileNumberCleaned);
            mobileNumber.setStatus(MobileNumber.Status.CORRECTED);
            return mobileNumberRepository.save(mobileNumber);
        }
        mobileNumber.setStatus(MobileNumber.Status.NOT_VALID);
        return mobileNumber;
    }

    protected boolean isValid(String mobileNumber) {
        return mobileNumber.length() == 11 && Utils.isAllNumbers(mobileNumber) && mobileNumber.startsWith("27");
    }

    protected String cleanNumber(String mobileNumber) {
        var mobileNumberFiltered = Utils.filterNumbers(mobileNumber);
        try {
            var startIndex = mobileNumberFiltered.indexOf("27");
            if (startIndex < 0) {
                return "";
            }
            return mobileNumberFiltered.substring(startIndex, startIndex + 11);
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }
}
