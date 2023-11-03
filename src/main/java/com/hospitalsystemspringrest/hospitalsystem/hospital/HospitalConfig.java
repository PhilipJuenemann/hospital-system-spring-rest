package com.hospitalsystemspringrest.hospitalsystem.hospital;

import com.hospitalsystemspringrest.hospitalsystem.exception.PatientNotFoundException;
import com.hospitalsystemspringrest.hospitalsystem.patient.Patient;
import com.hospitalsystemspringrest.hospitalsystem.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Configuration
public class HospitalConfig {

    private final HospitalController hospitalController;

    @Autowired
    public HospitalConfig(HospitalController hospitalController) {
        this.hospitalController = hospitalController;
    }

    @Bean
    CommandLineRunner commandLineRunnerHospital(
            HospitalRepository hospitalRepository,
            PatientRepository patientRepository) {
        return args -> {
            Hospital sieveking = new Hospital(
                            "Amalie Sieveking",
                            20,
                            7);

            Hospital lmuKlinikum = new Hospital(
                    "LMUKlinikum",
                    255,
                    67);

            Patient patient = new Patient(
                    "Philip",
                    "Jünemann",
                    LocalDate.of(2003, Month.DECEMBER, 30),
                    "philip.juenemanns@gmail.com"
            );

            patientRepository.save(patient);

            hospitalRepository.saveAll(List.of(sieveking, lmuKlinikum));

            hospitalController.registerPatientToHospital(sieveking, patient);
        };


    }
}
