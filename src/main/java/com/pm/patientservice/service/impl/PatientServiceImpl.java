package com.pm.patientservice.service.impl;

import com.pm.patientservice.dtos.PatientRequestDto;
import com.pm.patientservice.dtos.PatientResponseDto;
import com.pm.patientservice.enums.ResponseCode;
import com.pm.patientservice.exception.PatientSaveFailedException;
import com.pm.patientservice.exception.RetrivedFailedException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repo.PatientRepo;
import com.pm.patientservice.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) throws PatientSaveFailedException {
        log.info("createPatient service...");
        try {
            Patient patient = PatientMapper.toPatient(patientRequestDto);
            Patient newPatient = patientRepo.save(patient);

            return PatientMapper.toDto(newPatient);
        } catch (Exception e) {
            log.error("error occured while creating patient error: ", e.getMessage());
            ResponseCode.PATIENT_SAVE_FAIL.setReason(e.getMessage());
            throw new PatientSaveFailedException(ResponseCode.PATIENT_SAVE_FAIL);
        }
    }
}
