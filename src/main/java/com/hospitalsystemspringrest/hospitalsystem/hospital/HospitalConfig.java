package com.hospitalsystemspringrest.hospitalsystem.hospital;

import com.hospitalsystemspringrest.hospitalsystem.patient.Patient;
import com.hospitalsystemspringrest.hospitalsystem.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Configuration
public class HospitalConfig {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalConfig(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @Bean
    CommandLineRunner commandLineRunnerPatient(
            PatientRepository repository) {
        return args -> {
            Patient philip = new Patient(
                    "Philip",
                    "Jünemann",
                    LocalDate.of(2003, Month.DECEMBER, 30),
                    "philip.juenemanns@gmail.com"
            );

            repository.save(philip);
        };
    }

    @Bean
    CommandLineRunner commandLineRunner(
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

            hospitalRepository.saveAll(List.of(sieveking, lmuKlinikum));

            Optional<Patient> philip = patientRepository
                    .findByFirstNameAndLastName("Philip", "Jünemann");

            System.out.println("______-----------------------------------------_" + philip);

            hospitalService.registerPatientToHospital(sieveking.getId(), philip.orElseThrow().getId());
        };


    }
}
