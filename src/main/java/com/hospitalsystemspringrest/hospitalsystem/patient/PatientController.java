package com.hospitalsystemspringrest.hospitalsystem.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/patient")
public class PatientController {
    private final PatientService patientService;
    private final PatientRepository patientRepository;

    @Autowired
    public PatientController(PatientService patientService, PatientRepository patientRepository) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
    }

    @PostMapping(path = "/create")
    public void createNewPatient(@RequestBody Patient patient) {
        patientService.createNewPatient(patient);
    }

    @DeleteMapping(path = "/{patientId}")
    public void deletePatient(@PathVariable Long patientId) {
        patientService.deletePatient(patientId);
    }

    @PutMapping(path = "/{patientId}")
    public void modifyPatient(@PathVariable Long patientId, @RequestBody Patient patient) {
        patientService.modifyPatient(patientId, patient);
    }

    @GetMapping
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }
}
