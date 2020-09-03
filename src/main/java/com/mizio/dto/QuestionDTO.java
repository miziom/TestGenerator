package com.mizio.dto;

import com.mizio.model.AnswerCorrect;
import com.mizio.model.QuestionType;
import javafx.scene.control.CheckBox;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QuestionDTO {

    private int questionID;
    private String questionName;
    private QuestionType questionType;
    private AnswersContentDTO answersContentDTO;
    private AnswerCorrect answerCorrect;
    private ImageDTO imageDTO;
    private CheckBox checkBox;
}