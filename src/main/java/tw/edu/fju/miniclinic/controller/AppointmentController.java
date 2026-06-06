package tw.edu.fju.miniclinic.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tw.edu.fju.miniclinic.model.*;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class AppointmentController {

    @Autowired
    DoctorRepository doctorRepo;

    @Autowired
    PatientRepository patientRepo;

    @Autowired
    AppointmentRepository appointmentRepo;

    @GetMapping("/appointment/new")
    public String form(Model model){

        model.addAttribute(
                "form",
                new AppointmentForm()
        );

        model.addAttribute(
                "doctors",
                doctorRepo.findAll()
        );

        return "appointment-new";
    }

    @PostMapping("/appointment/new")
    public String submit(

            @Valid
@ModelAttribute
AppointmentForm form,

BindingResult result,

Model model

    ){
if (result.hasErrors()) {

    model.addAttribute(
            "doctors",
            doctorRepo.findAll()
    );

    return "appointment-new";
}

        Patient patient =
                patientRepo
                        .findById(form.getChartNo())
                        .orElse(null);

        Doctor doctor =
                doctorRepo
                        .findById(form.getDoctorId())
                        .orElse(null);

        if(patient==null || doctor==null){

    model.addAttribute(
            "form",
            form
    );

    model.addAttribute(
            "doctors",
            doctorRepo.findAll()
    );

    model.addAttribute(
            "error",
            "找不到病患或醫師"
    );

    return "appointment-new";
}

        Appointment appt =
                new Appointment();

        appt.setPatient(patient);

        appt.setDoctor(doctor);

        appt.setApptDate(
                LocalDate.parse(
                        form.getApptDate()
                )
        );

        appt.setTimeSlot(
                form.getTimeSlot()
        );

        appt.setStatus(
                "BOOKED"
        );

        appointmentRepo.save(
                appt
        );

        try{

    Appointment saved =
            appointmentRepo.save(
                    appt
            );

    model.addAttribute(
            "appointment",
            saved
    );

    return "appointment-result";

}catch(Exception e){

    model.addAttribute(
            "error",
            "掛號失敗"
    );

    model.addAttribute(
            "form",
            form
    );

    model.addAttribute(
            "doctors",
            doctorRepo.findAll()
    );

    return "appointment-new";
}

    }

}