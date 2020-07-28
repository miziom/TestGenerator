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

    @Column(name = "subject_name",
            nullable = false)
    private String subjectName;

    @OneToMany(mappedBy = "subject",
            targetEntity = Test.class,
            fetch= FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.PERSIST)
    private List<Test> tests = new ArrayList<>();

    @Override
    public String toString() {
        return subjectName.toUpperCase();
    }
}
