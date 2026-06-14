package tw.edu.fju.miniclinic.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.edu.fju.miniclinic.model.AppointmentRepository;
import tw.edu.fju.miniclinic.model.DoctorRepository;
import tw.edu.fju.miniclinic.model.PatientRepository;

@RestController
public class StatsController {

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private AppointmentRepository appointmentRepo;

    @GetMapping("/api/stats")
    public Map<String, Object> stats() {

        Map<String, Object> result =
                new HashMap<>();

        result.put(
                "totalDoctors",
                doctorRepo.count()
        );

        result.put(
        "totalPatients",
        patientRepo.findAll().size()
);

        result.put(
                "totalAppointments",
                appointmentRepo.count()
        );

        Map<String, Long> byStatus =
                new HashMap<>();

        byStatus.put(
                "BOOKED",
                appointmentRepo.countByStatus("BOOKED")
        );

        byStatus.put(
                "COMPLETED",
                appointmentRepo.countByStatus("COMPLETED")
        );

        byStatus.put(
                "CANCELLED",
                appointmentRepo.countByStatus("CANCELLED")
        );

        result.put(
                "byStatus",
                byStatus
        );

        return result;
    }
}