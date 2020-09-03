package com.mizio.manager;

import com.mizio.dto.QuestionDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Getter
@Setter
public class DrawManager {

    private Integer numberOfGroup;
    private List<QuestionDTO> questionDTOList;
    private List<QuestionDTO> groupFirst;
    private List<QuestionDTO> groupSecond;
    private List<QuestionDTO> groupThird;

    public DrawManager(Integer numberOfGroup, List<QuestionDTO> questionDTOList) {
        this.numberOfGroup = numberOfGroup;
        this.questionDTOList = questionDTOList;
    }

    public static List<QuestionDTO> drawQuestionList(List<QuestionDTO> questionDTOListIn, Integer amountOfQuestion, Integer amountOfGroup) {
        Random rand = new Random();
        List<QuestionDTO> questionDTOListOut = new ArrayList<>();
        List<QuestionDTO> questionDTOList = new ArrayList<>(questionDTOListIn);
        int maxImageAmount = RangeManager.countMaxImageAmount(questionDTOList, amountOfGroup);
        int imageCounter = 0;
        for (int i = 0; i < amountOfQuestion; i++) {
            int randomIndex = rand.nextInt(questionDTOList.size());
            if (maxImageAmount <= imageCounter) {
                while (questionDTOList.get(randomIndex).getImageDTO() != null) {
                    randomIndex = rand.nextInt(questionDTOList.size());
                }
            }
            if(questionDTOList.get(randomIndex).getImageDTO() != null) {
                imageCounter++;
            }
            questionDTOListOut.add(questionDTOList.get(randomIndex));
            questionDTOList.remove(randomIndex);
        }
        return questionDTOListOut;
    }

    public void drawGroups() {
        switch (this.getNumberOfGroup()) {
            case 1: {
                this.drawFirstGroup();
            } break;
            case 2: {
                this.drawFirstGroup();
                this.drawSecondGroup();
            } break;
            case 3: {
                this.drawFirstGroup();
                this.drawSecondGroup();
                this.drawThirdGroup();
            } break;
        }
    }

    private void drawFirstGroup() {
        List<QuestionDTO> questionDTOList = new ArrayList<>(this.getQuestionDTOList());
        Collections.shuffle(questionDTOList);
        this.setGroupFirst(questionDTOList);
    }

    private void drawSecondGroup() {
        List<QuestionDTO> questionDTOList = new ArrayList<>(this.getQuestionDTOList());
        Random rand = new Random();
        List<QuestionDTO> questionDTOListSecondGroup = new ArrayList<>();
        for (int i = 0; i < this.getQuestionDTOList().size(); i++) {
            int randomIndex = rand.nextInt(questionDTOList.size());
            if (this.isImageOnSlide(2, i)) {
                while (questionDTOList.get(randomIndex).getImageDTO() != null
                    || questionDTOList.get(randomIndex) == this.getGroupFirst().get(i)) {
                    randomIndex = rand.nextInt(questionDTOList.size());
                }
            } else {
                int indexQuestionWithIndex = isImageAllowed(questionDTOList);
                if (indexQuestionWithIndex != -1) {
                    randomIndex = indexQuestionWithIndex;
                }
                while (questionDTOList.get(randomIndex) == this.getGroupFirst().get(i)) {
                    randomIndex = rand.nextInt(questionDTOList.size());
                }
            }
            questionDTOListSecondGroup.add(questionDTOList.get(randomIndex));
            questionDTOList.remove(randomIndex);
        }
        this.setGroupSecond(questionDTOListSecondGroup);
    }

    private void drawThirdGroup() {
        List<QuestionDTO> questionDTOList = new ArrayList<>(this.getQuestionDTOList());
        Random rand = new Random();
        List<QuestionDTO> questionDTOListThirdGroup = new ArrayList<>();
        for (int i = 0; i < this.getQuestionDTOList().size(); i++) {
            int randomIndex = rand.nextInt(questionDTOList.size());
            if (this.isImageOnSlide(3, i)) {
                while (questionDTOList.get(randomIndex).getImageDTO() != null
                        || questionDTOList.get(randomIndex) == this.getGroupFirst().get(i)
                        || questionDTOList.get(randomIndex) == this.getGroupSecond().get(i)) {
                    randomIndex = rand.nextInt(questionDTOList.size());
                }
            } else {
                int indexQuestionWithIndex = isImageAllowed(questionDTOList);
                if (indexQuestionWithIndex != -1) {
                    randomIndex = indexQuestionWithIndex;
                }
                while (questionDTOList.get(randomIndex) == this.getGroupFirst().get(i)
                        || questionDTOList.get(randomIndex) == this.getGroupSecond().get(i)) {
                    randomIndex = rand.nextInt(questionDTOList.size());
                }
            }
            questionDTOListThirdGroup.add(questionDTOList.get(randomIndex));
            questionDTOList.remove(randomIndex);
        }
        this.setGroupThird(questionDTOListThirdGroup);
    }

    private boolean isImageOnSlide(int numberOfCurrentGroupToCreate, int slideNumber) {
        switch (numberOfCurrentGroupToCreate) {
            case 2: {
                return this.getGroupFirst().get(slideNumber).getImageDTO() != null;
            }
            case 3: {
                return this.getGroupFirst().get(slideNumber).getImageDTO() != null
                        || this.getGroupSecond().get(slideNumber).getImageDTO() != null;
            }
            default: {
                return false;
            }
        }
    }

    private int isImageAllowed(List<QuestionDTO> questionDTOList) {
        Random rand = new Random();
        int returnIndex = -1;
        List<QuestionDTO> questionDTOSWithImage = questionDTOList.stream()
                .filter(questionDTO -> questionDTO.getImageDTO() != null)
                .collect(Collectors.toList());
        if (questionDTOSWithImage.size() > 0) {
            int randomIndex = rand.nextInt(questionDTOSWithImage.size());
            returnIndex = questionDTOList.indexOf(questionDTOSWithImage.get(randomIndex));
        }
        return returnIndex;
    }
}
