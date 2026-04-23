package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.*;
import at.spengergasse.spring_thymeleaf.services.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final PatientRepository patientRepository;
    private final ModalityRespository modalityRepository;

    public ReservationController(ReservationService reservationService,
                                 PatientRepository patientRepository,
                                 ModalityRespository modalityRepository) {
        this.reservationService = reservationService;
        this.patientRepository = patientRepository;
        this.modalityRepository = modalityRepository;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("reservations", reservationService.findAll());
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
    public String save(@ModelAttribute Reservation reservation, Model model) {
        try {
            reservationService.save(reservation);
            return "redirect:/reservation/list";
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("patients", patientRepository.findAll());
            model.addAttribute("modalities", modalityRepository.findAll());
            return "add_reservation";
        }
    }
}