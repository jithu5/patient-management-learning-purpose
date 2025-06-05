package com.pm.PatientManager.controllers;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.pm.PatientManager.dto.PatientRequestDto;
import com.pm.PatientManager.dto.PatientResponseDto;
import com.pm.PatientManager.dto.validators.CreatePatientValidationGroup;
import com.pm.PatientManager.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public List<PatientResponseDto> getAll(){
        return patientService.getPatients();
    }

    @PostMapping("/create")
    public ResponseEntity<PatientResponseDto> createPatient(
            @Validated({Default.class, CreatePatientValidationGroup.class})
            @RequestBody PatientRequestDto newPatient){
        PatientResponseDto patient = patientService.createPatient(newPatient);
        return ResponseEntity.ok().body(patient);
    }


    @PutMapping("/{id}/update")
    public ResponseEntity<PatientResponseDto> updatePatientById(
        @PathVariable UUID id,
        @Validated(Default.class)
        @RequestBody PatientRequestDto patientRequestDto){
        PatientResponseDto updatedPatient = patientService.updatePatient(id, patientRequestDto);
        return ResponseEntity.ok().body(updatedPatient);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
