package com.hospitalsystemspringrest.hospitalsystem.patient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;
    private AutoCloseable autoCloseable;
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        patientService = new PatientService(patientRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllPatients() {
        patientService.getPatients();
        verify(patientRepository).findAll();
    }

    @Test
    @Disabled
    void createNewPatient() {
    }

    @Test
    @Disabled
    void deletePatient() {
    }

    @Test
    @Disabled
    void updatePatient() {
    }

    @Test
    @Disabled
    void getHospitalsOfPatient() {
    }
}