package com.jio.TLM.Service;

import com.jio.TLM.Entities.Odm;
import com.jio.TLM.Entities.Project;
import com.jio.TLM.Repository.OdmRepository;
import com.jio.TLM.Repository.ProjectRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

@Service
public class Project_Odm {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private OdmRepository odmRepository;

    public void projectToOdmMapping(String path) throws IOException {

        File excelFile = new File(path);

        if (!excelFile.exists()) {
            throw new IOException("Excel file not found: " + path);
        }

        FileInputStream fis = new FileInputStream(excelFile);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) {
            rowIterator.next(); // Comment this line if no header row is present
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if(isRowEmpty(row)){
                break;
            }
            String projectId = row.getCell(0).getStringCellValue();
            String odmId = row.getCell(3).getStringCellValue();
            if( projectId == null || odmId == null){
                break;
            }

            Odm odm = this.odmRepository.findById(odmId).orElseThrow();

            Project project = this.projectRepository.findById(projectId).orElseThrow();
            project.setOdm(odm);

            this.projectRepository.save(project);

        }

        workbook.close();

    }
    private boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}
