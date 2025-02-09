package com.jio.TLM.Service;

import com.jio.TLM.Entities.Odm;
import com.jio.TLM.Entities.Part;
import com.jio.TLM.Entities.Project;
import com.jio.TLM.Repository.PartRepository;
import com.jio.TLM.Repository.ProjectRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class Project_Part {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public void assignPartsToProjects(String path) throws IOException {

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
            Project tempProject = this.projectRepository.findById(row.getCell(0).getStringCellValue()).orElseThrow();
            List<Part> tempParts = new ArrayList<>();
//            tempParts = this.partRepository.findByProject(tempProject);
            for(int i=4; i<=5; i++){ //this has to be made variable not upto 5

                int partTempId = (int)row.getCell(i).getNumericCellValue();
                Part temp = this.partRepository.findById(partTempId).orElseThrow();
                tempParts.add(temp);

            }

            tempProject.setAllParts(tempParts);
            this.projectRepository.save(tempProject);

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
