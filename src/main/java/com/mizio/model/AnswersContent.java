package com.mizio.model;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "answers")
public class AnswersContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id",
            updatable = false,
            nullable = false)
    private int answerID;

    @ManyToOne(targetEntity = Subject.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    private Subject subject;

    @ManyToOne(targetEntity = Test.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id", referencedColumnName = "test_id")
    private Test test;

    @ManyToOne(targetEntity = Question.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Question question;

    @Column(name = "answer_a",
            nullable = false)
    private String answerA;

    @Column(name = "answer_b",
            nullable = false)
    private String answerB;

    @Column(name = "answer_c")
    private String answerC;

    @Column(name = "answer_d")
    private String answerD;

}
