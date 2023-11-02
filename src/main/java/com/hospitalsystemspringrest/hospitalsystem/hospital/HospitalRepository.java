package com.hospitalsystemspringrest.hospitalsystem.hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository
        extends JpaRepository<Hospital, Long> {
}
