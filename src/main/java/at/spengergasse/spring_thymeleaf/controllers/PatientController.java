package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Patient;
import at.spengergasse.spring_thymeleaf.entities.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/list")
    public String patients(Model model) {
        try {
            model.addAttribute("patients", patientRepository.findAll());
            return "patlist";
        } catch (DataAccessException e) {
            model.addAttribute("error", "Datenbankfehler! Bitte überprüfe ob MySQL läuft.");
            return "error";
        }
    }

    @GetMapping("/add")
    public String addPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "add_patient";
    }

    @PostMapping("/add")
    public String addPatient(@Valid @ModelAttribute("patient") Patient patient,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_patient";
        }
        try {
            patientRepository.save(patient);
            return "redirect:/patient/list";
        } catch (DataAccessException e) {
            model.addAttribute("error", "Database error! Please check if MySQL is running.");
            return "error";
        }
    }
}