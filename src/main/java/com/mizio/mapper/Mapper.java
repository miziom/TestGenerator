package com.mizio.mapper;

import com.mizio.dto.AnswersContentDTO;
import com.mizio.dto.ImageDTO;
import com.mizio.dto.QuestionDTO;
import com.mizio.model.AnswersContent;
import com.mizio.model.Image;
import com.mizio.model.Question;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public List<QuestionDTO> mapQuestionListToQuestionDTOList(List<Question> questionList) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question:questionList) {
            questionDTOList.add(mapQuestionToQuestionDTO(question));
        }
        return questionDTOList;
    }

    private QuestionDTO mapQuestionToQuestionDTO(Question question) {
        ImageDTO imageDTO = null;
        if (question.getImage() != null) {
            imageDTO = mapImageToImageDTO(question.getImage());
        }
        return QuestionDTO.builder()
                .questionID(question.getQuestionID())
                .questionName(question.getQuestionName())
                .questionType(question.getQuestionType())
                .answersContentDTO(mapAnswersContentToAnswersContentDTO(question.getAnswersContent()))
                .answerCorrect(question.getAnswerCorrect())
                .imageDTO(imageDTO)
                .checkBox(new CheckBox())
                .build();
    }

    private ImageDTO mapImageToImageDTO(Image image) {
        return ImageDTO.builder()
                .imageID(image.getImageID())
                .imageName(image.getImageName())
                .image(image.getImage())
                .build();
    }

    private AnswersContentDTO mapAnswersContentToAnswersContentDTO(AnswersContent answersContent) {
        return AnswersContentDTO.builder()
                .answerID(answersContent.getAnswerID())
                .answerA(answersContent.getAnswerA())
                .answerB(answersContent.getAnswerB())
                .answerC(answersContent.getAnswerC())
                .answerD(answersContent.getAnswerD())
                .build();
    }
}
