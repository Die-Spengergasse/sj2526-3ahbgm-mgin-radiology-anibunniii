package at.spengergasse.spring_thymeleaf.services;

import at.spengergasse.spring_thymeleaf.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Reservation reservation;

    @BeforeEach
    void setUp() {
        Patient patient = new Patient("Anna", "Muster", LocalDate.of(2000, 1, 1), 123456789L, "F");
        Modality modality = new Modality("MRI", 101);

        reservation = new Reservation();
        reservation.setPatient(patient);
        reservation.setModality(modality);
        reservation.setReservationTime(LocalDateTime.now().plusDays(1));
    }

    @Test
    void findAll() {
        when(reservationRepository.findAll()).thenReturn(List.of(reservation));

        List<Reservation> result = reservationService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void save() {
        when(reservationRepository.countOverlapping(anyLong(), any(), any())).thenReturn(0);
        when(reservationRepository.countPatientOverlapping(anyInt(), any(), any())).thenReturn(0);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation result = reservationService.save(reservation);

        assertNotNull(result);
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void save_throwsWhenInPast() {
        reservation.setReservationTime(LocalDateTime.now().minusDays(1));

        assertThrows(IllegalStateException.class, () -> reservationService.save(reservation));
        verify(reservationRepository, never()).save(any());
    }

    @Test
    void save_throwsWhenModalityConflict() {
        when(reservationRepository.countOverlapping(anyLong(), any(), any())).thenReturn(1);

        assertThrows(IllegalStateException.class, () -> reservationService.save(reservation));
        verify(reservationRepository, never()).save(any());
    }
        //
    @Test
    void save_throwsWhenPatientConflict() {
        when(reservationRepository.countOverlapping(anyLong(), any(), any())).thenReturn(0);
        when(reservationRepository.countPatientOverlapping(anyInt(), any(), any())).thenReturn(1);

        assertThrows(IllegalStateException.class, () -> reservationService.save(reservation));
        verify(reservationRepository, never()).save(any());
    }

    @Test
    void delete() {
        doNothing().when(reservationRepository).deleteById(1L);

        reservationService.delete(1L);

        verify(reservationRepository, times(1)).deleteById(1L);
    }
}