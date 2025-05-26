package com.pm.patientservice.service;

import com.pm.patientservice.dtos.PatientResponseDto;

import java.util.List;

public interface PatientService {
    List<PatientResponseDto> getPatients();
}
