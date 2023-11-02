package com.hospitalsystemspringrest.hospitalsystem.patient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class PatientConfig {

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
}
