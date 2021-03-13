package com.rampazzof.validator.mobile.number.validator.controller;

import com.rampazzof.validator.mobile.number.validator.model.DocumentValidationResponse;
import com.rampazzof.validator.mobile.number.validator.model.ValidationRequest;
import com.rampazzof.validator.mobile.number.validator.model.ValidationResponse;
import com.rampazzof.validator.mobile.number.validator.service.ValidatorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MobileNumberController {

    private final ValidatorService validatorService;

    @PostMapping(value = "/upload/csv", consumes = {"multipart/form-data"})
    @Operation(summary = "validate csv file containing list of south african mobile number")
    public DocumentValidationResponse validateDocument(@RequestParam("file") MultipartFile file) {
        try {
            MDC.clear();
            MDC.put("action", "validateDocument");
            log.info("validate document {}", file.getName());
            var fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
            var csvParser = new CSVParser(fileReader, CSVFormat.newFormat(',').withHeader());
            var validationResponseList = csvParser
                    .getRecords()
                    .parallelStream()
                    .map(row -> {
                        var validationResponse = new ValidationResponse();
                        return validationResponse.mapValidationResponse(validatorService.lightValidation(row.get("mobile_number")));
                    })
                    .collect(Collectors.toList());
            var documentValidationResponse = new DocumentValidationResponse();
            documentValidationResponse.setValidationResponseList(validationResponseList);
            return documentValidationResponse;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @PostMapping("/validate")
    @Operation(summary = "validate single south african mobile number")
    public ValidationResponse validateSingleMobileNumber(@Valid @RequestBody ValidationRequest validationRequest) {
        try {
            MDC.clear();
            MDC.put("action", "validateSingleMobileNumber");
            log.info("validate mobile number {}", validationRequest.getMobileNumber());
            var validationResponse = new ValidationResponse();
            return validationResponse.mapValidationResponse(validatorService.lightValidation(validationRequest.getMobileNumber()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
