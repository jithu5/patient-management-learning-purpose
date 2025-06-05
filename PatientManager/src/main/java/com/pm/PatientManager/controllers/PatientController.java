package com.pm.PatientManager.controllers;

import com.pm.PatientManager.dto.PatientRequestDto;
import com.pm.PatientManager.dto.PatientResponseDto;
import com.pm.PatientManager.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<PatientResponseDto> createPatient(@Valid @RequestBody PatientRequestDto newPatient){

        PatientResponseDto patient = patientService.createPatient(newPatient);
        return ResponseEntity.ok().body(patient);
    }
}
