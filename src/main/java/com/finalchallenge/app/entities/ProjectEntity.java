package com.finalchallenge.app.entities;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "projects")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private Long idProject;

    @Column(name = "customer", nullable = false, length = 40)
    private String customer;

    @Column(name = "technologies", nullable = false, length = 80)
    private String technologies;

    @Column(name = "limit_date", nullable = false, length = 50)
    private LocalDate limitDate;

    @OneToMany(mappedBy = "project", cascade = CascadeType.PERSIST)
    private List<EmployeeEntity> employeeList;

}
