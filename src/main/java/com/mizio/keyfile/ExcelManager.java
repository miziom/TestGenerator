package com.mizio.keyfile;

import com.mizio.manager.DrawManager;
import com.mizio.model.GroupDetail;
import com.mizio.pattern.KeyPattern;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

@Getter
@Setter
public class ExcelManager {

    private DrawManager drawManager;
    private List<GroupDetail> groupDetailList;
    private XSSFWorkbook workbook;

    public ExcelManager(DrawManager drawManager, List<GroupDetail> groupDetailList, XSSFWorkbook workbook) {
        this.drawManager = drawManager;
        this.groupDetailList = groupDetailList;
        this.workbook = workbook;
    }

    public void createKey() {
        KeyManager keyManager = new KeyManager();
        FormManager formManager = new FormManager();
        XSSFSheet spreadsheet = this.getWorkbook().createSheet(KeyPattern.TITLE_SHEET_KEY);
        int rowIterator =0;
        CellStyle cellStyle1 = keyManager.createCellStyle(this.getWorkbook());

        for(int i = 0; i < this.getDrawManager().getNumberOfGroup(); i++) {
            keyManager.createFirstRow(spreadsheet, this.getGroupDetailList().get(i), rowIterator++, this.getDrawManager().getQuestionDTOList().size());
            switch (i + 1) {
                case 1: {
                    keyManager.createSecondRow(spreadsheet, this.getDrawManager().getGroupFirst(), cellStyle1, rowIterator++);
                    keyManager.createThirdRow(spreadsheet, this.getDrawManager().getGroupFirst(), cellStyle1, rowIterator++);
                    rowIterator+=2;
                }break;
                case 2: {
                    keyManager.createSecondRow(spreadsheet, this.getDrawManager().getGroupSecond(), cellStyle1, rowIterator++);
                    keyManager.createThirdRow(spreadsheet, this.getDrawManager().getGroupSecond(), cellStyle1, rowIterator++);
                    rowIterator+=2;
                } break;
                case 3: {
                    keyManager.createSecondRow(spreadsheet, this.getDrawManager().getGroupThird(), cellStyle1, rowIterator++);
                    keyManager.createThirdRow(spreadsheet, this.getDrawManager().getGroupThird(), cellStyle1, rowIterator++);
                    rowIterator+=2;
                } break;
            }
        }
        spreadsheet.setDefaultColumnWidth(2);

        for(int i = 0; i < this.getDrawManager().getNumberOfGroup(); i++){
            spreadsheet = this.getWorkbook().createSheet(this.getGroupDetailList().get(i).getGroupName());
            spreadsheet.setMargin(Sheet.LeftMargin, 0);
            spreadsheet.setMargin(Sheet.RightMargin, 0);
            spreadsheet.setMargin(Sheet.TopMargin, 0);
            spreadsheet.setMargin(Sheet.BottomMargin, 0);
            rowIterator = 0;
            for(int j = 0; j< 11; j++){
                switch (i + 1) {
                    case 1: {
                        formManager.createFirstRow(spreadsheet, this.getDrawManager().getGroupFirst(), this.getGroupDetailList().get(i), 0, rowIterator++, false);
                        formManager.createSecondRow(spreadsheet, this.getDrawManager().getGroupFirst(),0, rowIterator++,false);
                        formManager.createThirdRow(spreadsheet, this.getDrawManager().getGroupFirst(), cellStyle1, 0, rowIterator++,false);
                        formManager.createFourthRow(spreadsheet, this.getDrawManager().getGroupFirst(), cellStyle1, 0, rowIterator,false);
                        rowIterator+=2;
                    }break;
                    case 2: {
                        formManager.createFirstRow(spreadsheet, this.getDrawManager().getGroupSecond(), this.getGroupDetailList().get(i), 0, rowIterator++, false);
                        formManager.createSecondRow(spreadsheet, this.getDrawManager().getGroupSecond(),0, rowIterator++,false);
                        formManager.createThirdRow(spreadsheet, this.getDrawManager().getGroupSecond(), cellStyle1, 0, rowIterator++,false);
                        formManager.createFourthRow(spreadsheet, this.getDrawManager().getGroupSecond(), cellStyle1, 0, rowIterator,false);
                        rowIterator+=2;
                    } break;
                    case 3: {
                        formManager.createFirstRow(spreadsheet, this.getDrawManager().getGroupThird(), this.getGroupDetailList().get(i), 0, rowIterator++, false);
                        formManager.createSecondRow(spreadsheet, this.getDrawManager().getGroupThird(),0, rowIterator++,false);
                        formManager.createThirdRow(spreadsheet, this.getDrawManager().getGroupThird(), cellStyle1, 0, rowIterator++,false);
                        formManager.createFourthRow(spreadsheet, this.getDrawManager().getGroupThird(), cellStyle1, 0, rowIterator,false);
                        rowIterator+=2;
                    } break;
                }
            }
            rowIterator = 0;
            if(this.getDrawManager().getQuestionDTOList().size() < 15){
                for(int k = 0; k <11; k++) {
                    switch (i + 1) {
                        case 1: {
                            formManager.createFirstRow(spreadsheet, this.getDrawManager().getGroupFirst(), this.getGroupDetailList().get(i), 0, rowIterator++, true);
                            formManager.createSecondRow(spreadsheet, this.getDrawManager().getGroupFirst(),0, rowIterator++,true);
                            formManager.createThirdRow(spreadsheet, this.getDrawManager().getGroupFirst(), cellStyle1, 0, rowIterator++,true);
                            formManager.createFourthRow(spreadsheet, this.getDrawManager().getGroupFirst(), cellStyle1, 0, rowIterator,true);
                            rowIterator+=2;
                        }break;
                        case 2: {
                            formManager.createFirstRow(spreadsheet, this.getDrawManager().getGroupSecond(), this.getGroupDetailList().get(i), 0, rowIterator++, true);
                            formManager.createSecondRow(spreadsheet, this.getDrawManager().getGroupSecond(),0, rowIterator++,true);
                            formManager.createThirdRow(spreadsheet, this.getDrawManager().getGroupSecond(), cellStyle1, 0, rowIterator++,true);
                            formManager.createFourthRow(spreadsheet, this.getDrawManager().getGroupSecond(), cellStyle1, 0, rowIterator,true);
                            rowIterator+=2;
                        } break;
                        case 3: {
                            formManager.createFirstRow(spreadsheet, this.getDrawManager().getGroupThird(), this.getGroupDetailList().get(i), 0, rowIterator++, true);
                            formManager.createSecondRow(spreadsheet, this.getDrawManager().getGroupThird(),0, rowIterator++,true);
                            formManager.createThirdRow(spreadsheet, this.getDrawManager().getGroupThird(), cellStyle1, 0, rowIterator++,true);
                            formManager.createFourthRow(spreadsheet, this.getDrawManager().getGroupThird(), cellStyle1, 0, rowIterator,true);
                            rowIterator+=2;
                        } break;
                    }
                }

            }
            spreadsheet.setDefaultColumnWidth(2);
        }
    }

}
