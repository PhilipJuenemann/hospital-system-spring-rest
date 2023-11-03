package com.hospitalsystemspringrest.hospitalsystem.hospital;

import com.hospitalsystemspringrest.hospitalsystem.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public List<Hospital> getHospitals() {
        return hospitalService.getHospitals();
    }

    @PostMapping("/create")
    public void createNewHospital(@RequestBody Hospital hospital) {
        hospitalService.addNewHospital(hospital);
    }

    @PostMapping("/register")
    public void registerPatientToHospital(Hospital hospital, Patient patient) {
        hospitalService.registerPatientToHospital(hospital, patient);
    }
}
