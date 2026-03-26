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

    @Column (name = "p_firstname")
    private String firstname;
    @Column (name = "p_lastname")
    private String lastname;
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

    public int getId() {
        return id;
    }

    public Patient(String firstname, String lastname, LocalDate birth, long ssn, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birth = birth;
        this.ssn = ssn;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birth=" + birth +
                ", ssn=" + ssn +
                ", gender='" + gender + '\'' +
                ", reservations=" + reservations +
                '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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



    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
}
