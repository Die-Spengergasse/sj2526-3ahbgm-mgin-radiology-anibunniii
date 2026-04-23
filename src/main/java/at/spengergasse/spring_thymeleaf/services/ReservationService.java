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

        boolean conflict = reservationRepository.countOverlapping(
                (long) reservation.getModality().getId(),
                start,
                end
        ) > 0;

        if (conflict) {
            throw new IllegalStateException("Dieses Gerät ist in diesem Zeitraum bereits belegt!");
        }

        return reservationRepository.save(reservation);
    }

    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }
}