package pl.agarlacz.ehealthcare.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "password", nullable = false)
    private String password;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userId", updatable = false, insertable = false)
    private PatientDetailsEntity patientDetails;
}
