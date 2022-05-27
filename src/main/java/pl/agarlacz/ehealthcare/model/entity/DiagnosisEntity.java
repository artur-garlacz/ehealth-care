package pl.agarlacz.ehealthcare.model.entity;

import javax.persistence.*;

public class DiagnosisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "description", nullable = false)
    private String description;
    private long patientDetailsId;
}
