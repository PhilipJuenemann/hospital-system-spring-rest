package com.hospitalsystemspringrest.hospitalsystem.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void addNewHospital(Hospital hospital) {
        Optional<Hospital> hospitalOptional = hospitalRepository
                .findHospitalByName(hospital.getName());
        if(hospitalOptional.isPresent()) {
            throw new IllegalStateException("Hospital already added");
        }
        hospitalRepository.save(hospital);
    }
}
