package com.hospitalsystemspringrest.hospitalsystem.hospital;

import com.hospitalsystemspringrest.hospitalsystem.patient.Patient;
import com.hospitalsystemspringrest.hospitalsystem.patient.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

    public void registerPatientToHospital(Hospital hospital, Patient patient) {
        hospital.getPatients().add(patient);
        patient.getHospitals().add(hospital);

        hospitalRepository.save(hospital);
    }

    @Transactional
    public void deleteHospital(Long hospitalId) {
        boolean exists = hospitalRepository.existsById(hospitalId);
        if (!exists) {
            throw new IllegalStateException("Hospital with ID " + hospitalId + " does not exist");
        }
        hospitalRepository.deleteById(hospitalId);
    }

    public void updateHospital(Long hospitalId, String name, Integer totalBeds, Integer occupiedBeds) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new IllegalStateException("Hospital with ID " + hospitalId + " does not exist."));

        if (name != null && !name.isEmpty() && !Objects.equals(hospital.getName(), name)) {
            hospital.setName(name);
        }

        if (totalBeds != null && totalBeds > 0 && !Objects.equals(hospital.getTotalBeds(), totalBeds)) {
            hospital.setTotalBeds(totalBeds);
        }

        if (occupiedBeds != null && occupiedBeds >= 0 && !Objects.equals(hospital.getOccupiedBeds(), occupiedBeds)) {
            hospital.setOccupiedBeds(occupiedBeds);
        }

        hospitalRepository.save(hospital);
    }
}
