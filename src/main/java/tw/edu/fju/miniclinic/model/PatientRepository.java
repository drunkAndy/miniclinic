package tw.edu.fju.miniclinic.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class PatientRepository {

    private List<Patient> patients = List.of(

    new Patient(
        "TEST00001",
        "王小明",
        "男",
        "0912345678",
        LocalDate.of(2000,1,1)
    ),

    new Patient(
        "TEST00002",
        "陳小華",
        "女",
        "0922333444",
        LocalDate.of(1999,5,10)
    ),

    new Patient(
        "TEST00003",
        "林大同",
        "男",
        "0933555666",
        LocalDate.of(2001,12,25)
    )
);

    public List<Patient> findAll() {
        return patients;
    
    
}

    public Optional<Patient> findById(String chartNo) {

        return patients
                .stream()
                .filter(
                    p -> p.getChartNo()
                    .equals(chartNo)
                )
                .findFirst();
    }
}