package com.pm.PatientManager.service;

import com.pm.PatientManager.dto.PatientRequestDto;
import com.pm.PatientManager.dto.PatientResponseDto;
import com.pm.PatientManager.exception.EmailAlreadyExistException;
import com.pm.PatientManager.exception.PatientNotFoundException;
import com.pm.PatientManager.mapper.PatientMapper;
import com.pm.PatientManager.models.Patient;
import com.pm.PatientManager.repository.PatientRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepo patientRepo;

    public PatientService(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }


    public List<PatientResponseDto> getPatients() {
        List<Patient> patients = patientRepo.findAll();

        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDto createPatient(PatientRequestDto patientRequestDTO){
        if (patientRepo.existsByEmail(patientRequestDTO.getEmail()))
            throw new EmailAlreadyExistException("Email already exists "+ patientRequestDTO.getEmail());
        Patient newPatient = patientRepo.save(
                PatientMapper.toModel(patientRequestDTO));
        return  PatientMapper.toDTO(newPatient);
    }

    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto){
        Patient patient = patientRepo.findById(id).orElseThrow(
                ()-> new PatientNotFoundException("Patient not exists by id ")
        );

        if (patientRepo.existsByEmailAndIdNot(patientRequestDto.getEmail(),id))
            throw new EmailAlreadyExistException("Email already exists "+ patientRequestDto.getEmail() +" with another id");

        patient.setName(patientRequestDto.getName());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));

        Patient updatedPatient = patientRepo.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }

    public void deletePatient(UUID id){
        patientRepo.deleteById(id);
    }
}
