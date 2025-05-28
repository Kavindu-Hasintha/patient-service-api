package com.pm.patientservice.service;

import com.pm.patientservice.dtos.PatientResponseDto;
import com.pm.patientservice.exception.RetrivedFailedException;

import java.util.List;

public interface PatientService {
    List<PatientResponseDto> getPatients() throws RetrivedFailedException;
}
