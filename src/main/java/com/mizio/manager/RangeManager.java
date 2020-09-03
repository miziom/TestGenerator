package com.mizio.manager;

import com.mizio.dto.QuestionDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RangeManager {

    public static List<Integer> fillIntegerList(int startInclusive, int endInclusive) {
        Stream<Integer> stream = IntStream.range(startInclusive, endInclusive + 1).boxed();
        return stream.collect(Collectors.toList());
    }

    public static int countMaxQuestionNumber(List<QuestionDTO> questionDTOList, Integer amountOfGroup) {
        int maxImageAmount = countMaxImageAmount(questionDTOList, amountOfGroup);
        int numberQuestionWithImage = Math.toIntExact(
                questionDTOList.stream()
                        .filter(questionDTO -> questionDTO.getImageDTO() != null)
                        .count()
        );
        if (numberQuestionWithImage > maxImageAmount) {
            return questionDTOList.size() - (numberQuestionWithImage - maxImageAmount);
        } else {
            return questionDTOList.size();
        }
    }

    public static int countMaxImageAmount(List<QuestionDTO> questionDTOList, Integer amountOfGroup) {
        return questionDTOList.size() / amountOfGroup;
    }

    public static List<QuestionDTO> getSelectedQuestions(List<QuestionDTO> questionDTOS) {
        if (questionDTOS.isEmpty()) {
            return null;
        } else {
            return questionDTOS.stream()
                    .filter(questionDTO -> questionDTO.getCheckBox().isSelected())
                    .collect(Collectors.toList());
        }
    }
}