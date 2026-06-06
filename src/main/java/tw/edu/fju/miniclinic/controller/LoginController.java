package tw.edu.fju.miniclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import tw.edu.fju.miniclinic.model.LoginForm;
import tw.edu.fju.miniclinic.model.Doctor;
import tw.edu.fju.miniclinic.model.DoctorRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
private final DoctorRepository doctorRepository;
public LoginController(
        DoctorRepository doctorRepository) {

    this.doctorRepository = doctorRepository;
}
    @GetMapping("/login")
public String loginPage(Model model) {

    if (!model.containsAttribute("loginForm")) {
        model.addAttribute(
                "loginForm",
                new LoginForm()
        );
    }

    return "login";
}
@PostMapping("/login")
public String login(
        @Valid LoginForm form,
        BindingResult result,
        HttpSession session,
        Model model) {

    if (result.hasErrors()) {
    return "login";
}

Doctor doctor =
        doctorRepository.findByDoctorId(
                form.getDoctorId()
        );

        if (doctor == null) {

    model.addAttribute(
            "errorMessage",
            "醫師編號或密碼錯誤"
    );

    return "login";
}

if (!BCrypt.checkpw(
        form.getPassword(),
        doctor.getPasswordHash())) {

    model.addAttribute(
            "errorMessage",
            "醫師編號或密碼錯誤"
    );

    return "login";
}
session.setAttribute(
        "loggedInDoctorId",
        doctor.getDoctorId()
);

session.setAttribute(
        "loggedInDoctorName",
        doctor.getName()
);

return "redirect:/dashboard";
        }
@PostMapping("/logout")
public String logout(
        HttpSession session) {

    session.invalidate();

    return "redirect:/login";
}
}

