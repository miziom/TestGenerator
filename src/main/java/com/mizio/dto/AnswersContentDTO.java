package com.mizio.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AnswersContentDTO {

    private int answerID;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
}
