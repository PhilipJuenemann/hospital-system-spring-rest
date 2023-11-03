package com.hospitalsystemspringrest.hospitalsystem.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/patient")
public class PatientController {
    private final PatientService patientService;
    private final PatientRepository patientRepository;

    @Autowired
    public PatientController(PatientService patientService, PatientRepository patientRepository) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }
}
