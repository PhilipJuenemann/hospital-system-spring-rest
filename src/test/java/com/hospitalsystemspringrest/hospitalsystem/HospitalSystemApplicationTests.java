package com.hospitalsystemspringrest.hospitalsystem;

import com.hospitalsystemspringrest.hospitalsystem.hospital.Hospital;
import com.hospitalsystemspringrest.hospitalsystem.hospital.HospitalRepository;
import com.hospitalsystemspringrest.hospitalsystem.hospital.HospitalService;
import com.hospitalsystemspringrest.hospitalsystem.patient.Patient;
import com.hospitalsystemspringrest.hospitalsystem.patient.PatientRepository;
import com.hospitalsystemspringrest.hospitalsystem.patient.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class HospitalSystemApplicationTests {

	@Autowired
	private PatientService patientService;

	@Autowired
	private HospitalService hospitalService;

	@MockBean
	private PatientRepository patientRepository;

	@MockBean
	private HospitalRepository hospitalRepository;

	@Test
	public void getPatientsTest(){
		when(patientRepository.findAll()).thenReturn(Stream.of(
				new Patient(
						"Philip",
						"Jünemann",
						new HashSet<>(),
						LocalDate.of(2003, Month.DECEMBER, 30),
						"philip.juenemanns@gmail.com"
				),
				new Patient(
						"Nicolas",
						"Jakob",
						new HashSet<>(),
						LocalDate.of(2000, Month.JANUARY, 1),
						"nicolas.jacob@gmail.com"
				)).collect(Collectors.toList()));
		assertEquals(2, patientService.getPatients().size());
	}

	@Test
	public void getHospitalsTest() {
		when(hospitalRepository.findAll()).thenReturn(Stream.of(
				new Hospital(
						"Amalie Sieving",
						new HashSet<>(),
						20,
						7
				),
				new Hospital(
						"LMUKlinikum",
						new HashSet<>(),
						255,
						67)
		).collect(Collectors.toList()));
		assertEquals(2, hospitalService.getHospitals().size());
	}

}
