package com.pm.PatientManager.mapper;

import com.pm.PatientManager.dto.PatientRequestDto;
import com.pm.PatientManager.dto.PatientResponseDto;
import com.pm.PatientManager.models.Patient;
import com.pm.PatientManager.service.PatientService;

import java.time.LocalDate;


public class PatientMapper {
    public static PatientResponseDto toDTO(Patient patient){
        PatientResponseDto patientResponseDto = new PatientResponseDto();
        patientResponseDto.setId(String.valueOf(patient.getId()));
        patientResponseDto.setName(patient.getName());
        patientResponseDto.setAddress(patient.getAddress());
        patientResponseDto.setEmail(patient.getEmail());
        patientResponseDto.setDateOfBirth(patient.getDateOfBirth().toString());

        return patientResponseDto;
    }

    public static Patient toModel(PatientRequestDto patientRequest){
        Patient patient = new Patient();

        patient.setAddress(patientRequest.getAddress());
        patient.setName(patientRequest.getName());
        patient.setEmail(patientRequest.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequest.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequest.getRegisteredDate()));

        return  patient;
    }
}
