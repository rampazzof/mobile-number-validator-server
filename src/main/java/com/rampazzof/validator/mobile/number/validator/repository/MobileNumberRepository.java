package com.rampazzof.validator.mobile.number.validator.repository;

import com.rampazzof.validator.mobile.number.validator.dto.MobileNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MobileNumberRepository extends JpaRepository<MobileNumber, Long> {
    Optional<MobileNumber> findByRawMobileNumber(String mobileNumber);
}
