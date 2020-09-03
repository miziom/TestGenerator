package com.mizio.manager;

import com.mizio.dto.QuestionDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class ListManager {

    private List<QuestionDTO> questionDTOList;
    private long numberOfImage;
    private int maxNumberOfImage;

    public boolean checkNumberOfImagesInListIsCorrect(Integer amountOfGroup) {
        this.setNumberOfImage(this.questionDTOList.stream()
                .filter(questionDTO -> questionDTO.getImageDTO() != null)
                .count());
        this.setMaxNumberOfImage(RangeManager.countMaxImageAmount(this.questionDTOList, amountOfGroup));
        return this.numberOfImage <= this.maxNumberOfImage;
    }
}

