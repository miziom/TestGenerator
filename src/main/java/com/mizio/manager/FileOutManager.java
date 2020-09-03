package com.mizio.manager;

import com.mizio.pattern.FileNamePattern;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

@Getter
@Setter
public class FileOutManager {

    private XMLSlideShow ppt;
    private XSSFWorkbook workbook;
    private File file;

    public FileOutManager(XMLSlideShow ppt, XSSFWorkbook workbook, File file) {
        this.ppt = ppt;
        this.workbook = workbook;
        this.file = file;
    }

    @SneakyThrows
    public void saveFiles()  {
        String absolutePath = this.getFile().getAbsolutePath();
        File filePpt = new File(absolutePath + FileNamePattern.NAME_PRESENTATION);
        File fileWorkbook = new File(absolutePath +  FileNamePattern.NAME_EXCEL);
        FileOutputStream outPpt = new FileOutputStream(filePpt);
        this.getPpt().write(outPpt);
        outPpt.close();
        FileOutputStream outWorkbook = new FileOutputStream(fileWorkbook);
        this.getWorkbook().write(outWorkbook);
        outWorkbook.close();
    }
}
