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
@Table(name = "details")
public class DetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_details")
    private Long idDetails;

    @Column(name = "seniority", nullable = false, length = 15)
    private String seniority;

    @Column(name = "role", nullable = false, length = 25)
    private String role;

    @Column(name = "salary", nullable = false)
    private Long salary;

    @OneToOne(mappedBy = "employeeDetails")
    private EmployeeEntity employee;

}
