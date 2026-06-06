package tw.edu.fju.miniclinic.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository
        extends JpaRepository<Doctor, String> {

    @Query("SELECT DISTINCT d.department FROM Doctor d")
    List<String> findAllDepartments();

    List<Doctor> findByDepartment(
            String department
    );

    Doctor findByDoctorId(
            String doctorId
    );

}