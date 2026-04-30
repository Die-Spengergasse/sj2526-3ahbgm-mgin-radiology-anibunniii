package at.spengergasse.spring_thymeleaf.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = """
        SELECT COUNT(*) FROM reservation
        WHERE modality_id = :modalityId
        AND reservation_time < :end
        AND DATE_ADD(reservation_time, INTERVAL 30 MINUTE) > :start
        """, nativeQuery = true)
    int countOverlapping(@Param("modalityId") Long modalityId,
                         @Param("start") LocalDateTime start,
                         @Param("end") LocalDateTime end);

    @Query(value = """
        SELECT COUNT(*) FROM reservation
        WHERE patient_id = :patientId
        AND reservation_time < :end
        AND DATE_ADD(reservation_time, INTERVAL 30 MINUTE) > :start
        """, nativeQuery = true)
    int countPatientOverlapping(@Param("patientId") int patientId,
                                @Param("start") LocalDateTime start,
                                @Param("end") LocalDateTime end);
}