package com.hospitalsystemspringrest.hospitalsystem.hospital;

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

    @PostMapping
    public void createNewHospital(@RequestBody Hospital hospital) {
        hospitalService.addNewHospital(hospital);
    }
}
