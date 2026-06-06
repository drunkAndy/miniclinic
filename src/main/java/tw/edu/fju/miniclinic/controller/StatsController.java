package tw.edu.fju.miniclinic.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tw.edu.fju.miniclinic.model.*;

@Controller
public class StatsController {

    @Autowired
    DoctorRepository doctorRepo;

    @Autowired
    PatientRepository patientRepo;

    @Autowired
    AppointmentRepository appointmentRepo;

    @GetMapping("/stats")
    public String stats(
        Model model
    ){

        model.addAttribute(
            "doctorCount",
            doctorRepo.count()
        );

        model.addAttribute(
    "patientCount",
    patientRepo.findAll().size()
);

        model.addAttribute(
            "appointmentCount",
            appointmentRepo.count()
        );

        Map<String,Long> group=
            new HashMap<>();

        for(
            Appointment a:
            appointmentRepo.findAll()
        ){

            String dep=
                a.getDoctor()
                 .getDepartment();

            group.put(
                dep,
                group.getOrDefault(
                    dep,
                    0L
                )+1
            );

        }

        model.addAttribute(
            "group",
            group
        );

        return "stats";

    }

}