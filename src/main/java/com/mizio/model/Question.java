package com.mizio.model;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id",
            updatable = false,
            nullable = false)
    private int questionID;

    @ManyToOne(targetEntity = Subject.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    private Subject subject;

    @ManyToOne(targetEntity = Test.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id", referencedColumnName = "test_id")
    private Test test;

    @Column(name = "question_name",
            nullable = false)
    private String questionName;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type",
            nullable = false)
    private QuestionType questionType;

    @OneToOne(targetEntity = AnswersContent.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_id", referencedColumnName = "answer_id")
    private AnswersContent answersContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "answer_correct",
            nullable = false)
    private AnswerCorrect answerCorrect;

    @OneToOne(targetEntity =  Image.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "image_id")
    private Image image;
}
