package com.hospitalsystemspringrest.hospitalsystem.patient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void canAddNewPatient() {
        Patient patient = new Patient(
                "Philip",
                "Jünemann",
                new HashSet<>(),
                LocalDate.of(2003, Month.DECEMBER, 30),
                "philip.juenemanns@gmail.com"
        );
        when(patientRepository.findByFirstNameAndLastName(patient.getFirstName(), patient.getLastName()))
                .thenReturn(Optional.empty());

        patientService.addNewPatient(patient);

        verify(patientRepository).save(patient);
    }

    @Test
    void throwsExceptionWhenAddingPatientTwice() {
        Patient patient = new Patient(
                "Philip",
                "Jünemann",
                new HashSet<>(),
                LocalDate.of(2003, Month.DECEMBER, 30),
                "philip.juenemanns@gmail.com"
        );
        when(patientRepository.findByFirstNameAndLastName(patient.getFirstName(), patient.getLastName()))
                .thenReturn(Optional.of(patient));

        assertThrows(IllegalStateException.class, () -> patientService.addNewPatient(patient));
    }

    @Test
    @Disabled
    void deletePatient() {
    }

    @Test
    void shouldThrowExceptionWhenNonExistentPatientDeleted() {
        Long patientId = 1L;
        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> patientService.deletePatient(patientId));
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