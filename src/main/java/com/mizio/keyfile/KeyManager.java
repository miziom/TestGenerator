package com.mizio.keyfile;

import com.mizio.dto.QuestionDTO;
import com.mizio.model.GroupDetail;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class KeyManager {


    public CellStyle createCellStyle(XSSFWorkbook workbook){
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    public void createFirstRow(XSSFSheet spreadsheet, GroupDetail groupDetail, int rowIterator, int testSize){
        XSSFRow rowFirst = spreadsheet.createRow(rowIterator);
        spreadsheet.addMergedRegion(new CellRangeAddress(rowIterator, rowIterator, 0,  testSize - 1));
        Cell cellGroupName = rowFirst.createCell(0);
        cellGroupName.setCellValue(groupDetail.getGroupName());
    }

    public void createSecondRow(XSSFSheet spreadsheet, List<QuestionDTO> test, CellStyle cellStyle, int rowIterator){
        XSSFRow rowThird = spreadsheet.createRow(rowIterator);
        int cellIterator = 0;
        for(QuestionDTO question:test){
            Cell cell = rowThird.createCell(cellIterator);
            cell.setCellValue(question.getAnswerCorrect().toString().toUpperCase());
            cell.setCellStyle(cellStyle);
            cellIterator++;
        }
    }

    public void createThirdRow(XSSFSheet spreadsheet, List<QuestionDTO> test, CellStyle cellStyle, int rowIterator){
        XSSFRow rowSecond = spreadsheet.createRow(rowIterator);
        for(int i=0; i<test.size(); i++){
            Cell cell = rowSecond.createCell(i);
            cell.setCellValue(i+1);
            cell.setCellStyle(cellStyle);
        }

    }
}
