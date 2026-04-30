package at.spengergasse.spring_thymeleaf.services;

import at.spengergasse.spring_thymeleaf.entities.Reservation;
import at.spengergasse.spring_thymeleaf.entities.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation save(Reservation reservation) {
        LocalDateTime start = reservation.getReservationTime();
        LocalDateTime end = start.plusMinutes(30);

        if (start.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Reservation cannot be in the past!");
        }

        boolean modalityConflict = reservationRepository.countOverlapping(
                (long) reservation.getModality().getId(), start, end) > 0;

        if (modalityConflict) {
            throw new IllegalStateException("This modality is already reserved for this time slot!");
        }

        boolean patientConflict = reservationRepository.countPatientOverlapping(
                reservation.getPatient().getId(), start, end) > 0;

        if (patientConflict) {
            throw new IllegalStateException("This Patient has already a reservation for this time slot!");
        }

        return reservationRepository.save(reservation);
    }

    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }
}