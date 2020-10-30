package com.mizio.examfile;

import com.mizio.dto.QuestionDTO;
import com.mizio.model.QuestionType;
import com.mizio.pattern.SlidePattern;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.xslf.usermodel.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SliderManager {

    private double fontSize;
    private int bottomPosQuestNumShape;
    private PresentationManager presentationManager;
    private XSLFSlide slide;
    private Rectangle imageRectangle;

    public SliderManager(PresentationManager presentationManager) {
        this.fontSize = 28.;
        this.bottomPosQuestNumShape = 0;
        this.presentationManager = presentationManager;
    }

    public void createSlide(int slideNumber) {
        this.slide = this.getPresentationManager().getPpt().createSlide();
        this.addImageIfExist(slideNumber);
        this.writeQuestionNumber(slideNumber + 1);
        this.writeQuestion(1, slideNumber);
        if (this.getPresentationManager().getDrawManager().getNumberOfGroup() >= 2) {
            this.writeQuestion(2, slideNumber);
        }
        if (this.getPresentationManager().getDrawManager().getNumberOfGroup() == 3) {
            this.writeQuestion(3, slideNumber);
        }

    }

    private void addImageIfExist(int slideNumber) {
        if (this.getPresentationManager().getDrawManager().getGroupFirst().get(slideNumber).getImageDTO() != null) {
            this.addImage(slideNumber, this.getPresentationManager().getDrawManager().getGroupFirst());
        }
        if (this.getPresentationManager().getDrawManager().getNumberOfGroup() >= 2) {
            if (this.getPresentationManager().getDrawManager().getGroupSecond().get(slideNumber).getImageDTO() != null){
                this.addImage(slideNumber, this.getPresentationManager().getDrawManager().getGroupSecond());
            }
        }
        if (this.getPresentationManager().getDrawManager().getNumberOfGroup() == 3) {
            if (this.getPresentationManager().getDrawManager().getGroupThird().get(slideNumber).getImageDTO() != null){
                this.addImage(slideNumber, this.getPresentationManager().getDrawManager().getGroupThird());
            }
        }
    }

    private void addImage(int slideNumber, List<QuestionDTO> questionDTOList) {
        XSLFPictureData pd = this.getPresentationManager().getPpt().addPicture(
                questionDTOList.get(slideNumber).getImageDTO().getImage(),
                XSLFPictureData.PictureType.PNG);
        XSLFPictureShape pic = this.getSlide().createPicture(pd);
        int originalPicWidth = pic.getPictureData().getImageDimensionInPixels().width;
        int originalPicHeight = pic.getPictureData().getImageDimensionInPixels().height;
        Rectangle rectangle = new Rectangle(780,0, originalPicWidth, originalPicHeight);
        setImageSizeAndPos(rectangle);
        pic.setAnchor(rectangle);
        this.imageRectangle = rectangle;
    }

    private void setImageSizeAndPos(Rectangle rectangle){

        if(rectangle.getSize().width < 500){
            double scale = (500 / rectangle.getWidth());
            int widthNew = (int)(rectangle.getWidth() * scale);
            int heightNew = (int)(rectangle.getHeight() * scale);
            rectangle.setSize(widthNew, heightNew);
            setImagePosition(rectangle);
        }

        if(rectangle.getSize().width > 500){
            double scale = (500/rectangle.getWidth());
            int widthNew = (int)(rectangle.getWidth() * scale);
            int heightNew = (int)(rectangle.getHeight() * scale);
            rectangle.setSize(widthNew, heightNew);
            setImagePosition(rectangle);
        }

        if(rectangle.getSize().height > 720){
            double scale = (720 / rectangle.getHeight());
            int widthNew = (int)(rectangle.getWidth() * scale);
            int heightNew = (int)(rectangle.getHeight() * scale);
            rectangle.setSize(widthNew, heightNew);
            setImagePosition(rectangle);
        }
    }

    private void setImagePosition(Rectangle rectangle){
        int xPos = 1280 - rectangle.getSize().width;
        rectangle.setLocation(xPos, 0);
    }

    private void writeQuestionNumber(int slideNumber){
        XSLFTextShape shape = this.createShape(0,0,780,100);
        XSLFTextParagraph paragraph = shape.addNewTextParagraph();
        XSLFTextRun r = paragraph.addNewTextRun();
        r.setText(String.format(SlidePattern.SLIDE_NUMBER, slideNumber));
        r.setFontColor(Color.black);
        r.setFontSize(this.getFontSize());
        r.setBold(true);
        paragraph.addLineBreak();
        Rectangle2D shapeRectangle = shape.resizeToFitText();
        this.bottomPosQuestNumShape = shapeRectangle.getBounds().height;
    }

    private XSLFTextBox createShape(int posX, int posY, int width, int height){
        XSLFTextBox shape = this.getSlide().createTextBox();
        shape.clearText();
        shape.setAnchor(new Rectangle(posX, posY, width, height));
        return shape;
    }

    private void writeQuestion(int groupNumber, int slideNumber) {
        int shapeWidth = 1280;
        int rectHeight = 0;
        if (this.getImageRectangle() != null) {
            shapeWidth = shapeWidth - this.getImageRectangle().width;
            rectHeight = this.getImageRectangle().height;
        }
        XSLFTextShape shape;
        if(this.getBottomPosQuestNumShape() > rectHeight){
            shape = createShape(0, this.getBottomPosQuestNumShape(), 1280, 100);
        }
        else {
            shape = createShape(0, this.getBottomPosQuestNumShape(), shapeWidth, 100);
        }
        XSLFTextParagraph paragraph = shape.addNewTextParagraph();
        XSLFTextRun r = paragraph.addNewTextRun();
        switch (groupNumber) {
            case 1: {
                r.setText(this.getPresentationManager().getDrawManager().getGroupFirst().get(slideNumber).getQuestionName());
            } break;
            case 2: {
                r.setText(this.getPresentationManager().getDrawManager().getGroupSecond().get(slideNumber).getQuestionName());
            }break;
            case 3: {
                r.setText(this.getPresentationManager().getDrawManager().getGroupThird().get(slideNumber).getQuestionName());
            }break;
        }
        r.setFontColor(this.getPresentationManager().getGroupDetailList().get(groupNumber-1).getGroupColor());
        r.setFontSize(this.getFontSize());
        paragraph.addLineBreak();
        this.writeAnswers(groupNumber, slideNumber, paragraph);
        shape.resizeToFitText();
        this.bottomPosQuestNumShape = this.getBottomPosQuestNumShape() + (int)shape.resizeToFitText().getHeight();
    }

    private void writeAnswers(int groupNumber, int slideNumber, XSLFTextParagraph paragraph) {
        ArrayList<String> answers;
        switch (groupNumber) {
            case 1: {
                answers = getAnswersAsArray(this.getPresentationManager().getDrawManager().getGroupFirst(), slideNumber);
            } break;
            case 2: {
                answers = getAnswersAsArray(this.getPresentationManager().getDrawManager().getGroupSecond(), slideNumber);
            }break;
            case 3: {
                answers = getAnswersAsArray(this.getPresentationManager().getDrawManager().getGroupThird(), slideNumber);
            }break;
            default:
                throw new IllegalStateException("Unexpected value: " + groupNumber);
        }
            for(int i = 0; i< answers.size(); i++){
                XSLFTextRun r = paragraph.addNewTextRun();
                r.setText(fillAnswer(i, answers.get(i)));
                r.setFontColor(this.getPresentationManager().getGroupDetailList().get(groupNumber-1).getGroupColor());
                r.setFontSize(this.getFontSize());
            }

    }

    private String fillAnswer(int number, String answer) {
        switch (number){
            case 0: {
                return String.format(SlidePattern.ANSWER_A, answer);
            }
            case 1:{
                return String.format(SlidePattern.ANSWER_B, answer);
            }
            case 2:{
                return String.format(SlidePattern.ANSWER_C, answer);
            }
            case 3:{
                return String.format(SlidePattern.ANSWER_D, answer);
            }
            default: {
                return SlidePattern.ANSWER_ERROR;
            }
        }
    }

    private ArrayList<String> getAnswersAsArray(List<QuestionDTO> questionDTOList, int slideNumber) {
        ArrayList<String> answers = new ArrayList<>();
        if (questionDTOList.get(slideNumber).getQuestionType() == QuestionType.AB) {
            answers.add(questionDTOList.get(slideNumber).getAnswersContentDTO().getAnswerA());
            answers.add(questionDTOList.get(slideNumber).getAnswersContentDTO().getAnswerB());
        } else if (questionDTOList.get(slideNumber).getQuestionType() == QuestionType.ABC) {
            answers.add(questionDTOList.get(slideNumber).getAnswersContentDTO().getAnswerA());
            answers.add(questionDTOList.get(slideNumber).getAnswersContentDTO().getAnswerB());
            answers.add(questionDTOList.get(slideNumber).getAnswersContentDTO().getAnswerC());
        } else if (questionDTOList.get(slideNumber).getQuestionType() == QuestionType.ABCD) {
            answers.add(questionDTOList.get(slideNumber).getAnswersContentDTO().getAnswerA());
            answers.add(questionDTOList.get(slideNumber).getAnswersContentDTO().getAnswerB());
            answers.add(questionDTOList.get(slideNumber).getAnswersContentDTO().getAnswerC());
            answers.add(questionDTOList.get(slideNumber).getAnswersContentDTO().getAnswerD());
        }
        return answers;
    }
}
