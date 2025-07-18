package com.pm.patientservice.mapper;

import com.pm.patientservice.dtos.PatientRequestDto;
import com.pm.patientservice.dtos.PatientResponseDto;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponseDto toDto(Patient patient) {
        PatientResponseDto patientResponseDto = new PatientResponseDto();
        patientResponseDto.setId(patient.getId().toString());
        patientResponseDto.setName(patient.getName());
        patientResponseDto.setEmail(patient.getEmail());
        patientResponseDto.setAddress(patient.getAddress());
        patientResponseDto.setDateOfBirth(patient.getDateOfBirth().toString());

        return patientResponseDto;
    }

    public static Patient toPatient(PatientRequestDto patientRequestDto) {
        Patient patient = new Patient();
        patient.setName(patientRequestDto.getName());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDto.getRegisterDate()));
        return patient;
    }
}
