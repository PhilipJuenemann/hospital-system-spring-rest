package com.hospitalsystemspringrest.hospitalsystem.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE p.firstName = ?1 AND p.lastName = ?2")
    Optional<Patient> findByFirstNameAndLastName(String firstName, String lastName);
}
