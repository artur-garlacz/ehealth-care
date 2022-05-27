package pl.agarlacz.ehealthcare.model.entity;

import lombok.Getter;
import lombok.Setter;
import pl.agarlacz.ehealthcare.model.enums.EmployeeRole;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "employees")
public class EmployeeEntity {
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
    private EmployeeRole employeeRole;
}
