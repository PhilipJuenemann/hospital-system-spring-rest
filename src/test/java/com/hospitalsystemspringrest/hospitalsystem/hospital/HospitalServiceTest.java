package com.hospitalsystemspringrest.hospitalsystem.hospital;

import com.hospitalsystemspringrest.hospitalsystem.patient.PatientRepository;
import com.hospitalsystemspringrest.hospitalsystem.patient.PatientService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class HospitalServiceTest {

    @Mock
    private HospitalRepository mockHospitalRepository;
    private AutoCloseable autoCloseable;
    private HospitalService hospitalService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        //hospitalService = new HospitalService(mockHospitalRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @Disabled
    void getHospitals() {
    }

    @Test
    @Disabled
    void createNewHospital() {
    }

    @Test
    @Disabled
    void deleteHospital() {
    }

    @Test
    @Disabled
    void updateHospital() {
    }

    @Test
    @Disabled
    void registerPatientToHospital() {
    }

    @Test
    @Disabled
    void getPatientsOfHospital() {
    }
}