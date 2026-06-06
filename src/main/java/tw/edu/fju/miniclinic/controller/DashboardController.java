package tw.edu.fju.miniclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

import tw.edu.fju.miniclinic.model.Appointment;
import tw.edu.fju.miniclinic.model.AppointmentRepository;
import tw.edu.fju.miniclinic.model.Doctor;
import tw.edu.fju.miniclinic.model.DoctorRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.crypto.bcrypt.BCrypt;

import tw.edu.fju.miniclinic.model.PasswordForm;

@Controller
public class DashboardController {
    private final DoctorRepository doctorRepository;
private final AppointmentRepository appointmentRepository;

public DashboardController(
        DoctorRepository doctorRepository,
        AppointmentRepository appointmentRepository) {

    this.doctorRepository = doctorRepository;
    this.appointmentRepository = appointmentRepository;
}

    @GetMapping("/dashboard")
public String dashboard(
        HttpSession session,
        Model model) {

    String doctorId =
            (String) session.getAttribute(
                    "loggedInDoctorId"
            );

    Doctor doctor =
            doctorRepository.findByDoctorId(
                    doctorId
            );
            if (doctor == null) {

    session.invalidate();

    return "redirect:/login";
}
LocalDate today =
        LocalDate.now();

List<Appointment> appointments =
        appointmentRepository
                .findByDoctorAndApptDate(
                        doctor,
                        today
                );
                model.addAttribute(
        "doctor",
        doctor
);

model.addAttribute(
        "appointments",
        appointments
);

model.addAttribute(
        "today",
        today
);

    return "dashboard";
}
@GetMapping("/password")
public String passwordPage(
        HttpSession session,
        Model model) {

    model.addAttribute(
            "loggedInDoctorName",
            session.getAttribute(
                    "loggedInDoctorName"
            )
    );

    model.addAttribute(
            "passwordForm",
            new PasswordForm()
    );

    return "password";
}

@PostMapping("/password")
public String changePassword(
        @ModelAttribute PasswordForm form,
        HttpSession session,
        Model model) {

    String doctorId =
            (String) session.getAttribute(
                    "loggedInDoctorId"
            );

    Doctor doctor =
            doctorRepository.findByDoctorId(
                    doctorId
            );

    if (!BCrypt.checkpw(
            form.getOldPassword(),
            doctor.getPasswordHash())) {

        model.addAttribute(
                "error",
                "舊密碼錯誤"
        );

        return "password";
    }

    if (!form.getNewPassword().equals(
            form.getConfirmPassword())) {

        model.addAttribute(
                "error",
                "兩次密碼不相符"
        );

        return "password";
    }

    if (form.getNewPassword().length() < 8) {

        model.addAttribute(
                "error",
                "密碼至少需要 8 個字元"
        );

        return "password";
    }

    doctor.setPasswordHash(
            BCrypt.hashpw(
                    form.getNewPassword(),
                    BCrypt.gensalt()
            )
    );

    doctorRepository.save(
            doctor
    );

    model.addAttribute(
            "success",
            "密碼修改成功"
    );

    return "password";
}
}
