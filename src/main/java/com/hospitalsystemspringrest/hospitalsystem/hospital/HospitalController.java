package com.hospitalsystemspringrest.hospitalsystem.hospital;

import com.hospitalsystemspringrest.hospitalsystem.common.Address;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/hospital")
public class HospitalController {

    @GetMapping
    public List<Hospital> getHospitals() {
        return List.of(
                new Hospital(
                        "Amalie Sieveking",
                        new Address("Heisenbergstraße, 3", "Munich", "Bayern", "Germany", "85748"),
                        Collections.emptyList(),
                        20,
                        7)
        );
    }
}
