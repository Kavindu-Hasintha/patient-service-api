package com.pm.patientservice.service.impl;

import com.pm.patientservice.dtos.PatientResponseDto;
import com.pm.patientservice.enums.ResponseCode;
import com.pm.patientservice.exception.RetrivedFailedException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repo.PatientRepo;
import com.pm.patientservice.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepo patientRepo;

    @Override
    public List<PatientResponseDto> getPatients() throws RetrivedFailedException {
        log.info("getPatients service...");
        try {
            List<Patient> patients = patientRepo.findAll();
            List<PatientResponseDto> patientResponseDtos = patients.stream()
                    .map(patient -> PatientMapper.toDto(patient)).toList();

//        List<PatientResponseDto> patientResponseDtos = patients.stream()
//                .map(PatientMapper::toDto).toList();

            return patientResponseDtos;
        } catch (Exception e) {
            log.error("error occured while getting patients. error: ", e.getMessage());
            ResponseCode.PATIENT_LIST_FAIL.setReason(e.getMessage());
            throw new RetrivedFailedException(ResponseCode.PATIENT_LIST_FAIL);
        }
    }
}
