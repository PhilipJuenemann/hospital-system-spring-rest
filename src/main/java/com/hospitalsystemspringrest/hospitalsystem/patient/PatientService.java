package com.hospitalsystemspringrest.hospitalsystem.patient;

import com.hospitalsystemspringrest.hospitalsystem.hospital.Hospital;
import com.hospitalsystemspringrest.hospitalsystem.hospital.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public void createNewPatient(Patient patient) {
        Optional<Patient> patientOptional = patientRepository.findByFirstNameAndLastName(
                patient.getFirstName(), patient.getLastName());
        if(patientOptional.isPresent()) {
            throw new IllegalStateException("Patient already added");
        }
        patientRepository.save(patient);
    }

    public void deletePatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalStateException("Patient with ID " + patientId + " does not exist"));

        patient.getHospitals().forEach(hospital -> hospital.getPatients().remove(patient));
        patient.setHospitals(new HashSet<>());
        patientRepository.deleteById(patientId);
    }


    public void updatePatient(Long patientId, Patient patient) {
        Patient existingPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalStateException("Patient with ID " + patientId + " does not exist"));
        if(patient.getFirstName() != null && !patient.getFirstName().isEmpty()) {
            existingPatient.setFirstName(patient.getFirstName());
        }
        if(patient.getLastName() != null && !patient.getLastName().isEmpty()) {
            existingPatient.setLastName(patient.getLastName());
        }
        if(patient.getEmail() != null && !patient.getEmail().isEmpty()) {
            existingPatient.setEmail(patient.getEmail());
        }
        if(patient.getDob() != null) {
            existingPatient.setDob(patient.getDob());
        }
        if(patient.getHospitals() != null && !patient.getHospitals().isEmpty()) {
            existingPatient.setHospitals(patient.getHospitals());
        }
        patientRepository.save(existingPatient);
    }

    public List<Hospital> getHospitalsOfPatient(Long patientId) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if(optionalPatient.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient with ID " + patientId + " not found");
        }
        return optionalPatient.get().getHospitals().stream().toList();
    }
}
