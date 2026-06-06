package tw.edu.fju.miniclinic.controller;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tw.edu.fju.miniclinic.model.*;

import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class AppointmentApiController {

    @Autowired
    AppointmentRepository appointmentRepo;

    @Autowired
    DoctorRepository doctorRepo;

    @GetMapping("/appointments/count")
    public Map<String,Long> count(){

        return Map.of(
            "count",
            appointmentRepo.count()
        );

    }

    @GetMapping("/appointments")
    public List<Appointment> appointments(

        @RequestParam(
            required=false
        )
        String date,

        @RequestParam(
            required=false
        )
        String doctorId

    ){

        if(
            date!=null
        ){

            return appointmentRepo
                .findByApptDate(
                    LocalDate.parse(
                        date
                    )
                );

        }

        if(
            doctorId!=null
        ){

            Doctor doctor=
                doctorRepo
                .findById(
                    doctorId
                )
                .orElse(null);

            if(
                doctor==null
            ){

                return List.of();

            }

            return appointmentRepo
                .findByDoctor(
                    doctor
                );

        }

        return appointmentRepo
            .findAll();

    }
@PutMapping("/appointments/{apptId}/status")
public ResponseEntity<?> updateStatus(

        @PathVariable Long apptId,

        @RequestBody Map<String,String> payload,

        HttpSession session

){

    // 從 Session 取得登入醫師 ID
    String loggedInDoctorId =
            (String) session.getAttribute(
                    "loggedInDoctorId"
            );

    // 查詢掛號資料
    Optional<Appointment> optionalAppt =
            appointmentRepo.findById(
                    apptId
            );

    if(optionalAppt.isEmpty()){

        return ResponseEntity
                .notFound()
                .build();

    }

    Appointment appt =
            optionalAppt.get();

    // 驗證是否為自己的掛號
    if(
            loggedInDoctorId == null ||
            !appt.getDoctor()
                    .getDoctorId()
                    .equals(loggedInDoctorId)
    ){

        return ResponseEntity
                .status(403)
                .build();

    }

    // 取得新狀態
    String newStatus =
            payload.get("status");

    List<String> validStatuses =
            Arrays.asList(
                    "BOOKED",
                    "COMPLETED",
                    "CANCELLED"
            );

    if(
            newStatus == null ||
            !validStatuses.contains(newStatus)
    ){

        return ResponseEntity
                .badRequest()
                .build();

    }

    // 更新狀態
    appt.setStatus(
            newStatus
    );

    appointmentRepo.save(
            appt
    );

    // 回傳更新後資料
    return ResponseEntity.ok(
            appt
    );

}
}