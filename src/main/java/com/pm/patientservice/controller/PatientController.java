package com.pm.patientservice.controller;

import com.pm.patientservice.dtos.APIResponseDto;
import com.pm.patientservice.dtos.PatientRequestDto;
import com.pm.patientservice.dtos.PatientResponseDto;
import com.pm.patientservice.dtos.PatientUpdateRequestDto;
import com.pm.patientservice.enums.ResponseCode;
import com.pm.patientservice.exception.*;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    @Tag(name = "PatientService", description = "Patient Service API")
    @Operation(summary = "Method used to create a patient")
    @PostMapping("/create-patient")
    public ResponseEntity<APIResponseDto> createPatient(@Valid @RequestBody PatientRequestDto patientRequestDto) {
        log.info("Create patient api...");
        PatientResponseDto patient = null;
        try {
            patient = patientService.createPatient(patientRequestDto);
        } catch (PatientSaveFailedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponseDto.getInstance(e.getResponseCode()));
        }
        ResponseCode.SUCCESS.setReason("Patient created successfully!");
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponseDto.getInstance(ResponseCode.SUCCESS, patient));
    }

    @Tag(name = "PatientService", description = "Patient Service API")
    @Operation(summary = "Method used to update a patient")
    @PutMapping("/update-patient/{id}")
    public ResponseEntity<APIResponseDto> updatePatient(@PathVariable UUID id,
                                                        @Valid @RequestBody PatientUpdateRequestDto requestDto) {
        log.info("Update patient api...");
        PatientResponseDto patient = null;
        try {
            patient = patientService.updatePatient(id, requestDto);
        } catch (PatientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponseDto.getInstance(e.getResponseCode()));
        } catch (PatientUpdateFailedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponseDto.getInstance(e.getResponseCode()));
        }
        ResponseCode.SUCCESS.setReason("Patient updated successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(APIResponseDto.getInstance(ResponseCode.SUCCESS, patient));
    }

    @Tag(name = "PatientService", description = "Patient Service API")
    @Operation(summary = "Method used to delete a patient")
    @DeleteMapping("/delete-patient/{id}")
    public ResponseEntity<APIResponseDto> deletePatient(@PathVariable UUID id) {
        log.info("Delete patient api...");
        try {
            patientService.deletePatient(id);
        } catch (PatientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponseDto.getInstance(e.getResponseCode()));
        } catch (PatientDeleteFailedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponseDto.getInstance(e.getResponseCode()));
        }
        ResponseCode.SUCCESS.setReason("Patient deleted successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(APIResponseDto.getInstance(ResponseCode.SUCCESS, null));
    }
}
