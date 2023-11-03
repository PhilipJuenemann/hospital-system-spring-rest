package com.hospitalsystemspringrest.hospitalsystem.hospital;

import com.hospitalsystemspringrest.hospitalsystem.patient.Patient;
import com.hospitalsystemspringrest.hospitalsystem.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.List;

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
            Hospital testDataHospitalOne = new Hospital(
                            "Amalie Sieveking",
                    new HashSet<>(),
                            20,
                            7);

            Hospital testDataHospitalTwo = new Hospital(
                    "LMUKlinikum",
                    new HashSet<>(),
                    255,
                    67);

            Patient testDataPatient = new Patient(
                    "Philip",
                    "Jünemann",
                    new HashSet<>(),
                    LocalDate.of(2003, Month.DECEMBER, 30),
                    "philip.juenemanns@gmail.com"
            );
            patientRepository.save(testDataPatient);
            hospitalRepository.saveAll(List.of(testDataHospitalOne, testDataHospitalTwo));
            //hospitalController.registerPatientToHospital(1L, 1L);
        };
    }
}
