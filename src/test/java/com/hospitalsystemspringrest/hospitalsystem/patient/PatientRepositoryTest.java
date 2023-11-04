package com.hospitalsystemspringrest.hospitalsystem.patient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientRepositoryTest {

    @Mock
    private PatientRepository mockPatientRepository;
    @Test
    void itShouldFindPatientByFirstNameAndLastName() {
        String firstName = "Philip";
        String lastName = "Jünemann";
        Patient patient = new Patient(
                firstName,
                lastName,
                new HashSet<>(),
                LocalDate.of(2003, Month.DECEMBER, 30),
                "philip.juenemanns@gmail.com"
        );
        when(mockPatientRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(Optional.of(patient));

        Optional<Patient> optionalPatient = mockPatientRepository.findByFirstNameAndLastName(firstName, lastName);

        assertTrue(optionalPatient.isPresent());
        assertEquals(patient, optionalPatient.get());
    }

    @Test
    public void itShouldNotFindStudentIfNotExists() {
        String firstName = "None Existent";
        String lastName = "None Existent";
        when(mockPatientRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(Optional.empty());

        Optional<Patient> optionalPatient = mockPatientRepository.findByFirstNameAndLastName(firstName, lastName);

        assertTrue(optionalPatient.isEmpty());



    }
}