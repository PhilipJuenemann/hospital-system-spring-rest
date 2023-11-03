package com.hospitalsystemspringrest.hospitalsystem.patient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hospitalsystemspringrest.hospitalsystem.common.Address;
import com.hospitalsystemspringrest.hospitalsystem.hospital.Hospital;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Patient {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long patientId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    //private Address address;
    private String email;
    @ManyToMany(mappedBy = "patients", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Hospital> hospitalsRegistered = new HashSet<>();

    @Transient
    private Integer age;

    public Patient() {
    }

    public Patient(String firstName, String lastName, LocalDate dob, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
    }

    public Long getId() {
        return patientId;
    }

    public void setId(Long id) {
        this.patientId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Hospital> getHospitals() {
        return hospitalsRegistered;
    }

    public void setHospitals(Set<Hospital> hospitalsRegistered) {
        this.hospitalsRegistered = hospitalsRegistered;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + patientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
