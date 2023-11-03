package com.hospitalsystemspringrest.hospitalsystem.patient;

import com.hospitalsystemspringrest.hospitalsystem.exception.PatientNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE p.firstName = ?1 AND p.lastName = ?2")
    Patient findByFirstNameAndLastName(String firstName, String lastName) throws PatientNotFoundException;
}
