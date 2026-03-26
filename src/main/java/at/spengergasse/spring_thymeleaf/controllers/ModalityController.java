package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Modality;
import at.spengergasse.spring_thymeleaf.entities.ModalityRespository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ModalityController {
    private final ModalityRespository modalityRespository;
    public  ModalityController(ModalityRespository modalityRespository)
    { this.modalityRespository = modalityRespository; }

    @GetMapping("/modality/add")
    public String showForm(Model model) {
        model.addAttribute("modality", new Modality());
        return "add_modality";
    }

    @PostMapping("/modality/add")
    public String save(@ModelAttribute Modality modality) {
        modalityRespository.save(modality);
        return "redirect:/modality/list";
    }

    @GetMapping("/modality/list")
    public String list(Model model) {
        model.addAttribute("modalities", modalityRespository.findAll());
        return "modalitylist";
    }
}