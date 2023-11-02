package com.hospitalsystemspringrest.hospitalsystem.hospital;

import com.hospitalsystemspringrest.hospitalsystem.common.Address;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class HospitalService {
    public List<Hospital> getHospitals() {
        return List.of(
                new Hospital(
                        "Amalie Sieveking",
                        20,
                        7)
        );
    }
}
