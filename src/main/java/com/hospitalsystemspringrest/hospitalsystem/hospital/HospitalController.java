package com.hospitalsystemspringrest.hospitalsystem.hospital;

import com.hospitalsystemspringrest.hospitalsystem.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/hospital")
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
        hospitalService.createNewHospital(hospital);
    }

    @DeleteMapping(path = "/{hospitalId}")
    public void deleteHospital(@PathVariable("hospitalId") Long hospitalId) {
        hospitalService.deleteHospital(hospitalId);
    }

    @PutMapping(path = "/{hospitalId}")
    public void updateHospital(
            @PathVariable("hospitalId") Long hospitalId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer totalBeds,
            @RequestParam(required = false) Integer occupiedBeds
    ) {
        hospitalService.updateHospital(hospitalId, name, totalBeds, occupiedBeds);
    }

    @PostMapping("/register")
    public void registerPatientToHospital(Hospital hospital, Patient patient) {
        hospitalService.registerPatientToHospital(hospital, patient);
    }

    @GetMapping(path = "patients/{hospitalId}")
    public List<Patient> getPatientsOfHospital(@PathVariable Long hospitalId) {
        return hospitalService.getPatientsOfHospital(hospitalId);
    }
}
