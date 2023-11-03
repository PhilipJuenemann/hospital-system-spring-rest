package com.hospitalsystemspringrest.hospitalsystem.patient;

import com.hospitalsystemspringrest.hospitalsystem.hospital.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
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
        patientService.updatePatient(patientId, patient);
    }

    @GetMapping
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    @GetMapping(path = "hospitals/{patientId}")
    public List<Hospital> getHospitalsOfPatient(@PathVariable Long patientId) {
        return patientService.getHospitalsOfPatient(patientId);
    }
}
