package tw.edu.fju.miniclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import tw.edu.fju.miniclinic.model.*;

import java.util.*;

@RestController
public class DoctorApiController {

    @Autowired
    private DoctorRepository repo;

    @GetMapping("/api/doctors")
    public List<Doctor> getDoctors(

            @RequestParam(required = false)
            String department

    ) {

        if (department == null) {
            return repo.findAll();
        }

        return repo.findByDepartment(department);
    }


    @GetMapping("/api/doctors/{doctorId}")
    public ResponseEntity<Doctor>

    getDoctor(

            @PathVariable
            String doctorId

    ) {

        return repo.findById(doctorId)

                .map(ResponseEntity::ok)

                .orElse(

                        ResponseEntity
                                .notFound()
                                .build()

                );

    }


    @GetMapping("/api/departments")
    public List<String> departments() {

        return repo.findAllDepartments();

    }

}