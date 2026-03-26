package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.*;

import at.spengergasse.spring_thymeleaf.entities.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationRepository reservationRepository;
   private final PatientRepository patientRepository;
    private final ModalityRespository modalityRepository;

    public ReservationController(ReservationRepository reservationRepository,
                                 PatientRepository patientRepository,
                                 ModalityRespository modalityRepository) {
        this.reservationRepository = reservationRepository;
        this.patientRepository = patientRepository;
        this.modalityRepository = modalityRepository;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("reservations", reservationRepository.findAll());
        return "reservationlist";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("modalities", modalityRepository.findAll());
        model.addAttribute("reservation", new Reservation());
        return "add_reservation";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Reservation reservation) {
        reservationRepository.save(reservation);
        return "redirect:/reservation/list";
    }
}