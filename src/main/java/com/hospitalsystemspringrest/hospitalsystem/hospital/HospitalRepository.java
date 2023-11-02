package com.hospitalsystemspringrest.hospitalsystem.hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalRepository
        extends JpaRepository<Hospital, Long> {

    @Query("SELECT h FROM Hospital h WHERE h.name = ?1")
    Optional<Hospital> findHospitalByName(String name);

}
