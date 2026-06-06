package tw.edu.fju.miniclinic.interceptor;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginRequiredInterceptor
implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws IOException {

        HttpSession session =
                request.getSession();

        String doctorId =
                (String) session.getAttribute(
                        "loggedInDoctorId"
                );

        if (doctorId != null) {
            return true;
        }

        String path =
                request.getRequestURI();

        if (path.startsWith("/api/")) {

            response.setStatus(401);

            response.setContentType(
                    "application/json"
            );

            response.getWriter().write(
                    "{\"error\":\"請先登入\"}"
            );

            return false;
        }

        response.sendRedirect("/login");

        return false;
    }
}
