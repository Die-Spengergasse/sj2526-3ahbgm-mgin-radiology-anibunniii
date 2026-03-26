package at.spengergasse.spring_thymeleaf.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table (name = "p_patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "p_name")
    private String name;
    @Column (name = "p_birth")
    private LocalDate birth;
    @Column(name = "p_ssn")
    private long ssn;
    @Column (name = "p_gender")
    private String gender;
    @OneToMany(mappedBy = "patient")
    private List<Reservation> reservations;
    public Patient() {
    }

    public Patient(String name, LocalDate birth, long ssn, String gender) {
        this.name = name;
        this.birth = birth;
        this.ssn = ssn;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", ssn=" + ssn +
                ", gender=" + gender +
                '}';
    }

    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
}
