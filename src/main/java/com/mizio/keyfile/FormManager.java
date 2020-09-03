package com.mizio.keyfile;

import com.mizio.dto.QuestionDTO;
import com.mizio.model.GroupDetail;
import com.mizio.pattern.KeyPattern;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;

public class FormManager {

    public void createFirstRow(XSSFSheet spreadsheet, List<QuestionDTO> test, GroupDetail groupDetail, int firstCol, int rowIterator, boolean secondRound){
        XSSFRow rowFirst;
        if(!secondRound){
            rowFirst = spreadsheet.createRow(rowIterator);
        }
        else {
            rowFirst = spreadsheet.getRow(rowIterator);
        }
        spreadsheet.addMergedRegion(new CellRangeAddress(rowIterator, rowIterator, firstCol,  firstCol + test.size()-1));
        Cell cellGroupName = rowFirst.createCell(firstCol);
        cellGroupName.setCellValue(groupDetail.getGroupName());
    }

    public void createSecondRow(XSSFSheet spreadsheet, List<QuestionDTO> test,int firstCol, int rowIterator, boolean secondRound){
        XSSFRow rowSecond;
        if(!secondRound){
            rowSecond = spreadsheet.createRow(rowIterator);
        }
        else {
            rowSecond = spreadsheet.getRow(rowIterator);
        }
        if(test.size() >= 16){
            Cell name = rowSecond.createCell(firstCol);
            name.setCellValue(KeyPattern.TITLE_NAME);
            Cell surname = rowSecond.createCell(firstCol+5);
            surname.setCellValue(KeyPattern.TITLE_SURNAME);
            Cell group = rowSecond.createCell(firstCol+14);
            group.setCellValue(KeyPattern.TITLE_GROUP);
            spreadsheet.addMergedRegion(new CellRangeAddress(rowIterator, rowIterator, firstCol,  firstCol+4));
            spreadsheet.addMergedRegion(new CellRangeAddress(rowIterator, rowIterator, firstCol+5,  firstCol+13));
            spreadsheet.addMergedRegion(new CellRangeAddress(rowIterator, rowIterator, firstCol+14,  firstCol+test.size()-1));
        }
        else{
            Cell surname = rowSecond.createCell(firstCol);
            surname.setCellValue(KeyPattern.TITLE_SURNAME);
            int pos = test.size()-3;
            if(pos <= 0){
                pos = 1;
            }
            Cell group = rowSecond.createCell(firstCol+pos);
            group.setCellValue(KeyPattern.TITLE_GROUP);
            spreadsheet.addMergedRegion(new CellRangeAddress(rowIterator, rowIterator, firstCol,  firstCol+pos-1));
            spreadsheet.addMergedRegion(new CellRangeAddress(rowIterator, rowIterator, firstCol+pos,  firstCol+test.size()-1));
        }
    }

    public void createThirdRow(XSSFSheet spreadsheet, List<QuestionDTO> test, CellStyle cellStyle,int firstCol, int rowIterator, boolean secondRound){
        XSSFRow rowThird;
        if(!secondRound){
            rowThird = spreadsheet.createRow(rowIterator);
        }
        else {
            rowThird = spreadsheet.getRow(rowIterator);
        }
        for(int i=0; i<test.size(); i++){
            Cell cell = rowThird.createCell(firstCol+i);
            cell.setCellValue(i+1);
            cell.setCellStyle(cellStyle);
        }
    }

    public void createFourthRow(XSSFSheet spreadsheet, List<QuestionDTO> test, CellStyle cellStyle, int firstCol, int rowIterator, boolean secondRound) {
        XSSFRow rowFourth;
        if(!secondRound){
            rowFourth = spreadsheet.createRow(rowIterator);
        }
        else {
            rowFourth = spreadsheet.getRow(rowIterator);
        }
        int cellIterator = 0;
        for (QuestionDTO question : test) {
            Cell cell = rowFourth.createCell(firstCol+cellIterator);
            cell.setCellStyle(cellStyle);
            cellIterator++;
        }
    }
}
