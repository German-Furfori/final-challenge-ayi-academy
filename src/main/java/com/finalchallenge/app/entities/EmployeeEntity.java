package com.finalchallenge.app.entities;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Long idEmployee;

    @Column(name = "dni", nullable = false, length = 8, unique = true)
    private String dni;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    private Boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_fk", referencedColumnName = "id_details")
    private DetailsEntity employeeDetails;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "project_fk", referencedColumnName = "id_project")
    private ProjectEntity project;

}
