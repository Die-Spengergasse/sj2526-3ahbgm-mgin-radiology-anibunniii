package at.spengergasse.spring_thymeleaf.services;
import at.spengergasse.spring_thymeleaf.entities.Modality;
import at.spengergasse.spring_thymeleaf.entities.Reservation;
import at.spengergasse.spring_thymeleaf.entities.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation save(Reservation reservation) {
        boolean conflict = reservationRepository.existsByModalityAndReservationTime(
                reservation.getModality(),
                reservation.getReservationTime()
        );

        if (conflict) {
            throw new IllegalStateException("Dieses Gerät ist zu dieser Uhrzeit bereits belegt!");
        }

        return reservationRepository.save(reservation);
    }

    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }
}