package com.pm.patientservice.controller;

import com.pm.patientservice.dtos.APIResponseDto;
import com.pm.patientservice.dtos.PatientResponseDto;
import com.pm.patientservice.enums.ResponseCode;
import com.pm.patientservice.exception.RetrivedFailedException;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/patient")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class PatientController {

    private final PatientService patientService;

    @Tag(name = "PatientService", description = "Patient Service API")
    @Operation(summary = "Method Used to get all patients")
    @GetMapping("/all")
    public ResponseEntity<APIResponseDto> getAllPatients() {
        log.info("Get all patients api...");
        List<PatientResponseDto> patients = null;
        try {
            patients = patientService.getPatients();
        } catch (RetrivedFailedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponseDto.getInstance(e.getResponseCode()));
        }
        ResponseCode.SUCCESS.setReason("Patients retrieved successfully!");
        return ResponseEntity.ok(APIResponseDto.getInstance(ResponseCode.SUCCESS, patients));
    }
}
