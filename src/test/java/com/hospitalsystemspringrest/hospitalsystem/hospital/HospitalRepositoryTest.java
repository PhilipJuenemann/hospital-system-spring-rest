package com.hospitalsystemspringrest.hospitalsystem.hospital;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HospitalRepositoryTest {

    @Mock
    private HospitalRepository mockHospitalRepository;

    @Test
    void itShouldFindHospitalByName() {
        String name = "Amalie Sieving";
        Hospital hospital = new Hospital(
                name,
                new HashSet<>(),
                20,
                7
        );
        when(mockHospitalRepository.findHospitalByName(name)).thenReturn(Optional.of(hospital));

        Optional<Hospital> optionalHospital = mockHospitalRepository.findHospitalByName(name);

        assertTrue(optionalHospital.isPresent());
        assertEquals(hospital, optionalHospital.get());
    }

    @Test
    void itShouldNotFindStudentIfNotExists() {
        String name = "NonExistent Hospital";
        when(mockHospitalRepository.findHospitalByName(name)).thenReturn(Optional.empty());

        Optional<Hospital> optionalHospital = mockHospitalRepository.findHospitalByName(name);

        assertTrue(optionalHospital.isEmpty());
    }
}