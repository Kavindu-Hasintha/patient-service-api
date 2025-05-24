package com.pm.patientservice.service.impl;

import com.pm.patientservice.dtos.PatientResponseDto;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repo.PatientRepo;
import com.pm.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepo patientRepo;

    public List<PatientResponseDto> getPatients() {
        List<Patient> patients = patientRepo.findAll();

        List<PatientResponseDto> patientResponseDtos = patients.stream()
                .map(patient -> PatientMapper.toDto(patient)).toList();

//        List<PatientResponseDto> patientResponseDtos = patients.stream()
//                .map(PatientMapper::toDto).toList();

        return patientResponseDtos;
    }
}
