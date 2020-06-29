package com.mizio.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id",
            updatable = false,
            nullable = false)
    private int testID;

    @ManyToOne(targetEntity = Subject.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    private Subject subject;

    @Column(name = "test_name",
            nullable = false)
    private String testName;

    @OneToMany(mappedBy = "test",
            targetEntity = Question.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();
}
