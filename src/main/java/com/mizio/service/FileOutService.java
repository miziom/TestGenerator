package com.mizio.service;

import com.mizio.dto.QuestionDTO;
import com.mizio.examfile.PresentationManager;
import com.mizio.keyfile.ExcelManager;
import com.mizio.manager.*;
import com.mizio.model.GroupDetail;
import com.mizio.pattern.LabelPattern;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.List;

@Setter
@Getter
public class FileOutService {

    private File saveFile;
    private List<GroupDetail> groupDetails;
    private Integer amountOfGroup;
    private DrawManager drawManager;

    public FileOutService(File saveFile, List<GroupDetail> groupDetails, Integer amountOfGroup) {
        this.saveFile = saveFile;
        this.groupDetails = groupDetails;
        this.amountOfGroup = amountOfGroup;
    }

    public void generateFilesForSelectedQuestions(List<QuestionDTO> questionDTOS) {
        List<QuestionDTO> selectedQuestions = RangeManager.getSelectedQuestions(questionDTOS);
        if (selectedQuestions == null) {
            PopUpManager.showInformation(LabelPattern.NO_QUESTIONS_SELECTED);
        } else {
            ListManager listManager = ListManager.builder()
                    .questionDTOList(selectedQuestions)
                    .build();
            if (listManager.checkNumberOfImagesInListIsCorrect(this.getAmountOfGroup())) {
                DrawManager drawManager = new DrawManager(
                        this.getAmountOfGroup(),
                        listManager.getQuestionDTOList()
                );
                this.setDrawManager(drawManager);
                this.finalizeFileSaving();
            } else {
                PopUpManager.showInformation(String.format(LabelPattern.IMAGE_NUMBER_ALERT,
                        listManager.getQuestionDTOList().size(),
                        this.getAmountOfGroup(),
                        listManager.getMaxNumberOfImage(),
                        listManager.getNumberOfImage()
                ));
            }
        }
    }

    public void generateFilesForDeterminedNumberOfQuestions(List<QuestionDTO> questionDTOListIn, Integer amountOfQuestion) {
        List<QuestionDTO> questionDTOList = DrawManager.drawQuestionList(
                questionDTOListIn,
                amountOfQuestion,
                this.getAmountOfGroup()
        );
        DrawManager drawManager = new DrawManager(
                this.getAmountOfGroup(),
                questionDTOList);
        this.setDrawManager(drawManager);
        this.finalizeFileSaving();
    }

    private void finalizeFileSaving() {
        this.getDrawManager().drawGroups();
        PresentationManager presentationManager = new PresentationManager(new XMLSlideShow(), this.getDrawManager(), this.getGroupDetails());
        presentationManager.createTest();
        ExcelManager excelManager = new ExcelManager(this.getDrawManager(), this.getGroupDetails(), new XSSFWorkbook());
        excelManager.createKey();
        FileOutManager fileOutManager = new FileOutManager(
                presentationManager.getPpt(),
                excelManager.getWorkbook(),
                this.getSaveFile());
        fileOutManager.saveFiles();
    }
}
