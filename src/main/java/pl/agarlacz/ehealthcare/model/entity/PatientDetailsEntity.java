package pl.agarlacz.ehealthcare.model.entity;

import lombok.Getter;
import lombok.Setter;
import pl.agarlacz.ehealthcare.model.enums.GenderType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "patient_details")
public class PatientDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate birthday;
    private GenderType gender;
    private double height;
    private double weight;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name="patientDetailsId", updatable = false, insertable = false)
    private List<DiagnosisEntity> diagnosisList;
}
