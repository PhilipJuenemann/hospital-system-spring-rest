package com.hospitalsystemspringrest.hospitalsystem.hospital;


import com.hospitalsystemspringrest.hospitalsystem.common.Address;
import com.hospitalsystemspringrest.hospitalsystem.patient.Patient;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.util.List;

public class Hospital {
    private Long id;
    private String name;
    private Address address;
    private List<Patient> patients;
    private Integer totalBeds;
    private Integer occupiedBeds;

    public Hospital() {
    }

    public Hospital(String name, Address address, List<Patient> patients, Integer totalBeds, Integer occupiedBeds) {
        this.name = name;
        this.address = address;
        this.patients = patients;
        this.totalBeds = totalBeds;
        this.occupiedBeds = occupiedBeds;
    }

    public Hospital(Long id, String name, Address address, List<Patient> patients, Integer totalBeds, Integer occupiedBeds) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.patients = patients;
        this.totalBeds = totalBeds;
        this.occupiedBeds = occupiedBeds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

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
}
