package com.mizio.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id",
            updatable = false,
            nullable = false)
    private int subjectID;

    @Column(name = "subject_name")
    private String subjectName;

    @OneToMany(mappedBy = "subject",
            targetEntity = Test.class,
            fetch= FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Test> tests = new ArrayList<>();

}
