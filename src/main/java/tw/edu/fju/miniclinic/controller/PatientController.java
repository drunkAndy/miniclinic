package tw.edu.fju.miniclinic.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.edu.fju.miniclinic.model.Patient;
import tw.edu.fju.miniclinic.model.PatientRepository;

@Controller
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(
        PatientRepository patientRepository
    ){
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patients")
    public String patients(Model model){

        model.addAttribute(
            "patients",
            patientRepository.findAll()
        );

        return "patients";
    }
@GetMapping("/api/patients")
@ResponseBody
public List<Patient> apiPatients() {
    return patientRepository.findAll();
}}