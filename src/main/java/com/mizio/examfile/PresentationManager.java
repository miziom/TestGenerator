package com.mizio.examfile;

import com.mizio.manager.DrawManager;
import com.mizio.model.GroupDetail;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.xslf.usermodel.XMLSlideShow;

import java.awt.*;
import java.util.List;

@Getter
@Setter
public class PresentationManager {

    XMLSlideShow ppt;
    DrawManager drawManager;
    List<GroupDetail> groupDetailList;

    public PresentationManager(XMLSlideShow ppt, DrawManager drawManager, List<GroupDetail> groupDetails) {
        this.ppt = ppt;
        this.drawManager = drawManager;
        this.groupDetailList = groupDetails;
        this.ppt.setPageSize(new Dimension(1280, 720));
    }

    public void createTest() {
        for (int slideNumber = 0; slideNumber < this.getDrawManager().getQuestionDTOList().size(); slideNumber++) {
            System.out.println(slideNumber);
            int counter = 0;
            SliderManager sliderManager = new SliderManager(this);
            sliderManager.createSlide(slideNumber);
            while (sliderManager.getBottomPosQuestNumShape() > 720) {
                counter++;
                this.getPpt().removeSlide(this.getPpt().getSlides().size() - 1);
                sliderManager = new SliderManager(this);
                sliderManager.setFontSize(sliderManager.getFontSize() - counter);
            }
        }
    }

}
