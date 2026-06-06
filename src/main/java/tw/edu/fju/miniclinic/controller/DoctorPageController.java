package tw.edu.fju.miniclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tw.edu.fju.miniclinic.model.DoctorRepository;

@Controller
public class DoctorPageController {

    private final DoctorRepository repo;

    public DoctorPageController(
        DoctorRepository repo
    ){
        this.repo = repo;
    }

    @GetMapping("/doctors")
    public String doctors(
        Model model
    ){

        model.addAttribute(
            "doctors",
            repo.findAll()
        );

        return "doctors";
    }
}
