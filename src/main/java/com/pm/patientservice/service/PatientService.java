package com.pm.patientservice.service;

import com.pm.patientservice.dtos.PatientRequestDto;
import com.pm.patientservice.dtos.PatientResponseDto;
import com.pm.patientservice.dtos.PatientUpdateRequestDto;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.exception.PatientSaveFailedException;
import com.pm.patientservice.exception.PatientUpdateFailedException;
import com.pm.patientservice.exception.RetrivedFailedException;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    List<PatientResponseDto> getPatients() throws RetrivedFailedException;
    PatientResponseDto createPatient(PatientRequestDto patientRequestDto) throws PatientSaveFailedException;
    PatientResponseDto updatePatient(UUID patientId, PatientUpdateRequestDto requestDto) throws PatientNotFoundException, PatientUpdateFailedException;
}
