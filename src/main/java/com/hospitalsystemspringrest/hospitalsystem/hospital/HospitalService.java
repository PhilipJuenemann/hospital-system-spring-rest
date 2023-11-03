package com.hospitalsystemspringrest.hospitalsystem.hospital;

import com.hospitalsystemspringrest.hospitalsystem.patient.Patient;
import com.hospitalsystemspringrest.hospitalsystem.patient.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public List<Hospital> getHospitals() {
        return hospitalRepository.findAll();
    }

    public void createNewHospital(Hospital hospital) {
        Optional<Hospital> hospitalOptional = hospitalRepository
                .findHospitalByName(hospital.getName());
        if(hospitalOptional.isPresent()) {
            throw new IllegalStateException("Hospital already added");
        }
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

    public void registerPatientToHospital(Hospital hospital, Patient patient) {
        hospital.getPatients().add(patient);
        patient.getHospitals().add(hospital);
        hospitalRepository.save(hospital);
    }

    public List<Patient> getPatientsOfHospital(Long hospitalId) {
        Optional<Hospital> optionalHospital = hospitalRepository.findById(hospitalId);
        if(optionalHospital.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient with ID " + hospitalId + " not found");
        }
        return optionalHospital.get().getPatients().stream().toList();
    }
}
