package com.hospitalsystemspringrest.hospitalsystem.patient;

import com.hospitalsystemspringrest.hospitalsystem.hospital.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    private final HospitalRepository hospitalRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, HospitalRepository hospitalRepository) {
        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
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

        hospitalRepository.saveAll(patient.getHospitals());

        patientRepository.deleteById(patientId);
    }


    public void modifyPatient(Long patientId, Patient patient) {
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
}
