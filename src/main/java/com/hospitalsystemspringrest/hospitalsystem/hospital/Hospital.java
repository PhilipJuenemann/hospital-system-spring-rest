package com.hospitalsystemspringrest.hospitalsystem.hospital;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hospitalsystemspringrest.hospitalsystem.patient.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "hospital_patient",
            joinColumns = @JoinColumn(name = "hospital_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    @JsonBackReference
    private Set<Patient> patients = new HashSet<>();

    private Integer totalBeds;
    private Integer occupiedBeds;

    public Hospital() {
    }

    public Hospital(String name, Set<Patient> patients, Integer totalBeds, Integer occupiedBeds) {
        this.name = name;
        this.patients = patients;
        this.totalBeds = totalBeds;
        this.occupiedBeds = occupiedBeds;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
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
