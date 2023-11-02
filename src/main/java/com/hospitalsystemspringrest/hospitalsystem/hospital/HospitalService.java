package com.hospitalsystemspringrest.hospitalsystem.hospital;

import com.hospitalsystemspringrest.hospitalsystem.patient.Patient;
import com.hospitalsystemspringrest.hospitalsystem.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    private final PatientRepository patientRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository, PatientRepository patientRepository) {
        this.hospitalRepository = hospitalRepository;
        this.patientRepository = patientRepository;
    }

    public List<Hospital> getHospitals() {
        return hospitalRepository.findAll();
    }

    public void addNewHospital(Hospital hospital) {
        Optional<Hospital> hospitalOptional = hospitalRepository
                .findHospitalByName(hospital.getName());
        if(hospitalOptional.isPresent()) {
            throw new IllegalStateException("Hospital already added");
        }
        hospitalRepository.save(hospital);
    }

    public void registerPatientToHospital(Long hospitalId, Long patientId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new RuntimeException("Hospital not found with id " + hospitalId));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id " + patientId));

        hospital.getPatients().add(patient);
        patient.getHospitals().add(hospital);

        hospitalRepository.save(hospital);
    }
}
