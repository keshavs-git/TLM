package com.jio.TLM.Service;

import com.jio.TLM.Entities.Odm;
//import com.jio.TLM.Entities.Part;
import com.jio.TLM.Entities.Project;
import com.jio.TLM.Repository.OdmRepository;
//import com.jio.TLM.Repository.PartRepository;
import com.jio.TLM.Repository.PartRepository;
import com.jio.TLM.Repository.ProjectRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


@Service
public class ExcelToDatabaseService {

    @Autowired
    private OdmRepository odmRepository;

    @Autowired
    private OdmService odmService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private PartService partService;

    public void processExcelFiles(String folderPath) throws IOException {
        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".xlsx"));

        if (files != null) {
            for (File file : files) {
                try (FileInputStream fis = new FileInputStream(file);
                     Workbook workbook = WorkbookFactory.create(fis)) {

                    Sheet sheet = workbook.getSheetAt(0);
                    String tableName = file.getName().replace(".xlsx", "").toLowerCase();

                    if ("odm".equals(tableName)) {
                        this.odmService.processOdmSheet(sheet);}
                    else if ("project".equals(tableName)) {
                        this.projectService.processProjectSheet(sheet);}
                    else if ("part".equals(tableName)) {
                        this.partService.processPartSheet(sheet);
                    }
                }

            }

        }

    }

//    private void processOdmSheet(Sheet sheet) {
//        Iterator<Row> rowIterator = sheet.iterator();
//        rowIterator.next(); // Skip header row
//
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//            Odm odm = new Odm();
//            odm.setOdm_id(row.getCell(0).getStringCellValue());
//            odm.setOdm_name(row.getCell(1).getStringCellValue());
//            odm.setOdm_description(row.getCell(2).getStringCellValue());
//
//            this.odmRepository.save(odm);
//
//        }
//    }

//    private void processProjectSheet(Sheet sheet) {
//        Iterator<Row> rowIterator = sheet.iterator();
//        rowIterator.next(); // Skip header row
//
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//            Project project = new Project();
//            project.setProject_id(row.getCell(0).getStringCellValue());
//            project.setProject_name(row.getCell(1).getStringCellValue());
//            project.setProject_description(row.getCell(2).getStringCellValue());
//            // Fetch and set the corresponding Odm entity
//            Odm odm = this.odmRepository.findById(row.getCell(3).getStringCellValue()).orElse(null);
//            project.setOdm(odm);
//
////            List<Part> allParts = this.partRepository.findByProject(project);
//
////            project.setParts(allParts);
//
//            projectRepository.save(project);
//        }
//    }

//    private void processPartSheet(Sheet sheet) {
//        Iterator<Row> rowIterator = sheet.iterator();
//        rowIterator.next(); // Skip header row
//
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//            Part part = new Part();
//            part.setPart_id((int) row.getCell(0).getNumericCellValue());
//            part.setPart_name(row.getCell(1).getStringCellValue());
//            part.setPart_description(row.getCell(2).getStringCellValue());
//            // Fetch and set the corresponding Project entity
//            Project project = this.projectRepository.findById(row.getCell(3).getStringCellValue()).orElse(null);
//            part.setProject(project);
//
//            partRepository.save(part);
//        }
//    }
}
