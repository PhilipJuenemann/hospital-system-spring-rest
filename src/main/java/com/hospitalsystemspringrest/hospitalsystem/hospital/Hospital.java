package com.hospitalsystemspringrest.hospitalsystem.hospital;


import com.hospitalsystemspringrest.hospitalsystem.common.Address;
import com.hospitalsystemspringrest.hospitalsystem.patient.Patient;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Hospital {

    @Id
    @SequenceGenerator(
            name = "hospital_sequence",
            sequenceName = "hospital_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hospital_sequence"
    )
    private Long hospitalId;
    private String name;
    //private Address address;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "hospital_patient",
            joinColumns = @JoinColumn(name = "hospital_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private Set<Patient> patients = new HashSet<>();
    private Integer totalBeds;
    private Integer occupiedBeds;

    public Hospital() {
    }

    public Hospital(String name, Integer totalBeds, Integer occupiedBeds) {
        this.name = name;
        /*this.address = address;
        this.patients = patients;*/
        this.totalBeds = totalBeds;
        this.occupiedBeds = occupiedBeds;
    }

    public Hospital(Long id, String name, Integer totalBeds, Integer occupiedBeds) {
        this.hospitalId = id;
        this.name = name;
        /*this.address = address;
        this.patients = patients;*/
        this.totalBeds = totalBeds;
        this.occupiedBeds = occupiedBeds;
    }

    public Long getId() {
        return hospitalId;
    }

    public void setId(Long id) {
        this.hospitalId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }*/

    /*public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }*/

    public Integer getTotalBeds() {
        return totalBeds;
    }

    public void setTotalBeds(Integer totalBeds) {
        this.totalBeds = totalBeds;
    }

    public Integer getOccupiedBeds() {
        return occupiedBeds;
    }

    public void setOccupiedBeds(Integer occupiedBeds) {
        this.occupiedBeds = occupiedBeds;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + hospitalId +
                ", name='" + name + '\'' +
                ", totalBeds=" + totalBeds +
                ", occupiedBeds=" + occupiedBeds +
                '}';
    }
}
