package com.mizio.model;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id",
            updatable = false,
            nullable = false)
    private int imageID;

    @ManyToOne(targetEntity = Subject.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    private Subject subject;

    @ManyToOne(targetEntity = Test.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id", referencedColumnName = "test_id")
    private Test test;

    @ManyToOne(targetEntity = Question.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Question question;

    @Column(name = "image_name")
    private String imageName;

    @Lob
    @Column(name = "image")
    private byte[] image;
}
