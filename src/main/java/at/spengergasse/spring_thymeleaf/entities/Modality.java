package at.spengergasse.spring_thymeleaf.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name= "m_modalities")
public class Modality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "m_id")
    private int id;
    @Column (name = "m_type")
    private String type;
    @Column (name = "m_roomnr")
    private int roomnr;
    @OneToMany(mappedBy = "modality")
    private List<Reservation> reservations;

    public Modality() {
    }

    public Modality(String type, int roomnr) {
        this.type = type;
        this.roomnr = roomnr;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Modality{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", roomnr=" + roomnr +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRoomnr() {
        return roomnr;
    }

    public void setRoomnr(int roomnr) {
        this.roomnr = roomnr;
    }
}
