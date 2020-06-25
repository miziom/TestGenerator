package com.mizio.model;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "students")
public class Subject {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private int subjectID;

    @Column(name = "subject_name")
    private String subjectName;

    @Override
    public String toString() {
        return "Subject{" +
                "subjectID=" + subjectID +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
