package com.hospitalsystemspringrest.hospitalsystem.hospital;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class HospitalConfig {

    @Bean
    CommandLineRunner commandLineRunner(HospitalRepository repository) {
        return args -> {
            Hospital sieveking = new Hospital(
                            "Amalie Sieveking",
                            20,
                            7);

            Hospital lmuKlinikum = new Hospital(
                    "LMUKlinikum",
                    255,
                    67);

            repository.saveAll(
                    List.of(sieveking, lmuKlinikum)
            );
        };


    }
}
